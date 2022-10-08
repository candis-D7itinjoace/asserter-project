package tn.sharing.asserterproject.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "verification_token")
@NoArgsConstructor
@Data
public class VerificationToken {
    
    

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    private int EXPIRATION_PERIOD = 30;

    private String token;

    private Date expirationTime;

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "user_id", nullable = false,
        foreignKey = @ForeignKey(name = "FK_USER_VERIFICATION_TOKEN")
    )
    private AppUser user;

    public VerificationToken(AppUser user, String token) {
        super();
        this.user = user;
        this.token = token;
        this.expirationTime = calculateExpirationTime(EXPIRATION_PERIOD);
    }

    public VerificationToken(String token){
        super();
        this.token = token;
        this.expirationTime = calculateExpirationTime(EXPIRATION_PERIOD);
    }

    private Date calculateExpirationTime(int expiration_period) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expiration_period);

        return new Date(calendar.getTime().getTime());
    }


}
