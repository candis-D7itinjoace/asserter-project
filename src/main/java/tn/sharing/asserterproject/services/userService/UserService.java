package tn.sharing.asserterproject.services.userService;


import tn.sharing.asserterproject.dto.AddOwnerModel;
import tn.sharing.asserterproject.entities.AppUser;
import tn.sharing.asserterproject.entities.VerificationToken;

public interface UserService {
    AppUser addOwner(AddOwnerModel ownerModel);

    AppUser findUserByEmail(String email) throws Exception;

    void saveVerificationTokenForUser(AppUser user, String token);

    String validateVerificationTokenForUser(String token);

    VerificationToken generateNewTokenForUser(String oldToken);

    boolean checkIfUserExists(Long userId);

//    Page<AppUser> getEmployeeList(int pageSize, int pageNumber);
}
