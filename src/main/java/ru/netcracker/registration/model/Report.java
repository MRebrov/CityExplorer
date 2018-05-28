package ru.netcracker.registration.model;


import javax.persistence.*;

@Entity
@Table(name= "report",schema = "nc_project")
public class Report {
    private Integer reportId;
    private User userId;
    private Quest questId;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @ManyToOne
    @JoinColumn(name = "quest_id", referencedColumnName = "quest_id", nullable = false)
    public Quest getQuestId() {
        return questId;
    }

    public void setQuestId(Quest questId) {
        this.questId = questId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        if (!reportId.equals(report.reportId)) return false;
        if (!userId.equals(report.userId)) return false;
        return questId.equals(report.questId);
    }

    @Override
    public int hashCode() {
        int result = reportId.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + questId.hashCode();
        return result;
    }
}
