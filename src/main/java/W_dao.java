import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "Vls")
public class W_dao implements Serializable {
    @Id
    @Column(name = "type")
    int type;

    @Id
    @Column(name = "dt")
    Timestamp dt;

    @Id
    @Column(name = "profileID")
    int profileID;

    @Id
    @Column(name = "Vl")
    float vl;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Timestamp getDt() {
        return dt;
    }

    public void setDt(Timestamp dt) {
        this.dt = dt;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public float getVl() {
        return vl;
    }

    public void setVl(float vl) {
        this.vl = vl;
    }

    @Override
    public String toString() {
        return "W_dao{" +
                "type=" + type +
                ", dt=" + dt.toLocalDateTime().toLocalDate() +
                ", profileID=" + profileID +
                ", vl=" + vl +
                '}';
    }
}
