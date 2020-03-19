package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "records")
@NamedQueries({
    @NamedQuery(
            name = "getAllRecords",
            query = "SELECT r FROM Record AS r ORDER BY r.id DESC"
            ),
    @NamedQuery(
            name = "getRecordsCount",
            query = "SELECT COUNT(r) FROM Record AS r"
            )
})

@Entity
public class Record {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private User user;

    @Column(name = "use_date", nullable = false)
    private Date use_date;

    @Column(name = "tranzaction", nullable = false)
    private String tranzaction;

    @Column(name = "overview", nullable = false)
    private String overview;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getUse_date() {
        return use_date;
    }

    public void setUse_date(Date use_date) {
        this.use_date = use_date;
    }

    public String getTranzaction() {
        return tranzaction;
    }

    public void setTranzaction(String tranzaction) {
        this.tranzaction = tranzaction;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

}
