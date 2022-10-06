package booking.az.dao.impl;

import booking.az.dao.DAO;
import booking.az.database.Database;
import booking.az.entities.User;

import java.util.List;
import java.util.Optional;


public class UserDAO implements DAO<User> {
    private final Database db;

    public UserDAO(Database db) {
        this.db = db;
    }

    @Override
    public List<User> getAll() {
        return db.getUsers();
    }

    @Override
    public User get(int id) {
        if (findById(id).isPresent()) {
            return findById(id).get();
        }
        return null;
    }

    @Override
    public boolean create(User user) {
        if (findByReference(user).isPresent()) return false;
        db.getUsers().add(user);
        return true;
    }

    @Override
    public boolean delete(User user) {
        if (findByReference(user).isPresent()) {
            db.getUsers().remove(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (findById(id).isPresent()) {
            db.getUsers().remove(findById(id).get());
            return true;
        }
        return false;
    }

    private Optional<User> findById(int id) {
        return db.getUsers().stream()
                .filter(user -> id == user.getId())
                .findFirst();
    }

    private Optional<User> findByReference(User user) {
        return db.getUsers().stream().filter(user::equals).findFirst();
    }
}
