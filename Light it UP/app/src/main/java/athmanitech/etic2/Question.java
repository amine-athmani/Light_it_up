package athmanitech.etic2;

/**
 * Created by Athmani on 11/02/2017.
 */
public class Question {
    private int id;
    private String titre;
    private String content;
    private int fk_user;
    private int fk_room;
    private int vote;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFk_user() {
        return fk_user;
    }

    public void setFk_user(int fk_user) {
        this.fk_user = fk_user;
    }

    public int getFk_room() {
        return fk_room;
    }

    public void setFk_room(int fk_room) {
        this.fk_room = fk_room;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
