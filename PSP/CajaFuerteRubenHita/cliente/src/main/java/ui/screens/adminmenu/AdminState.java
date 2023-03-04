package ui.screens.adminmenu;

import modelo.User;

import java.util.List;

public record AdminState(User user, List<User> userList, String error) {
}
