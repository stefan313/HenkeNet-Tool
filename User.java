
/**
 * class User
 *
 * User implements a single HenkeNet user account.
 *
 */

/**
 * @author nick
 *
 */
public class User {

    public String username;
    public String password;
    public String surname;
    public String givenname;
    public String room;
    public String email;
    public String expirationDate;

    public int user_id;
    public boolean isModified;

    public User(String username, String password, String room, String surname,
            String givenName, String email, String expirationDate) {
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.givenname = givenName;
        this.room = room;
        this.email = email;
        this.expirationDate = expirationDate;

        isModified = true;
    }

    public User(int userID, String username, String password, String room,
            String surname, String givenName, String email, String expirationDate) {
        // "User" instances with a given id refer to ones already existing
        // in the DB, hence the usual constructor is called and afterwards
        // isModified is set to false.

        //ARGGHH, NICK!
        // kann ich ja nich wissen, dass ich keine anderen konstruktoren
        // aufrufen kann.
        // Java ist einfach scheisse! nehmen wir C++?
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.givenname = givenName;
        this.room = room;
        this.email = email;
        this.expirationDate = expirationDate;
        this.user_id = userID;
        isModified = false;
    }

    /**
     *
     * @param dataLink
     */
    public void update(DataLink dataLink) {
        dataLink.update(this);
        isModified = false;
    }

    /*
     * @param dataLink   
     */
    public void insert(DataLink dataLink) {
        dataLink.insert(this);
        isModified = false;
    }

    /**
     * Insert this user as new user into the database.
     *
     * @param dataLink The data base connection.
     * @param t Transaction for this action. Must not be null.
     */
    public void insert(DataLink dataLink, Transaction t) {
        dataLink.insert(this);
        t.commit(dataLink);
    }

    /**
     * Extend the validity of an account to the given new expiration data.
     *
     * @param dataLink The data base connection.
     * @param expirationDate The new expiration date to be set.
     * @param t Transaction for this action. Must not be null.
     */
    public void extendValidity(DataLink dataLink, String expirationDate,
            Transaction t) {
        // TODO: sanitize data (expirationDate)
        this.expirationDate = expirationDate;
        this.update(dataLink);
        t.commit(dataLink);
    }

    /**
     * Deletes a user from the Database, if uids are equal
     *
     * @param dataLink The data base connection.
     * @param t Transaction. Can be null, if none occurred. (e.g. a user just
     * moved out, ...)
     */
    public void delete(DataLink dataLink, Transaction t) {
        dataLink.delete(this);
        if (t != null) {
            t.commit(dataLink);
        }
    }

    //Private Helper Methods
    private String prepareInsertStatement() {
        String statement = "INSERT INTO users SET ";

        statement += "username='" + username + "',";
        statement += "password='" + password + "',";
        statement += "room='" + room + "',";
        statement += "surname='" + surname + "',";
        statement += "givenname='" + givenname + "',";
        statement += "email='" + email + "',";
        statement += "expiration_date='" + expirationDate + "';";

        return statement;
    }

    private String prepareUpdateStatement() {
        String statement = "UPDATE users SET ";

        statement += "username='" + username + "',";
        statement += "password='" + password + "',";
        statement += "room='" + room + "',";
        statement += "surname='" + surname + "',";
        statement += "givenname='" + givenname + "',";
        statement += "email='" + email + "',";

        statement += "expiration_date="
                + (expirationDate == null
                        ? "NULL"
                        : "'" + expirationDate + "'");

        statement += " WHERE id=" + user_id + ";";

        return statement;
    }
}
