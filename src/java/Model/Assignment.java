/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 1396582
 */
@Entity
@Table(name = "assignment", catalog = "ats_hib", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assignment.findAll", query = "SELECT a FROM Assignment a")
    , @NamedQuery(name = "Assignment.findByAssgId", query = "SELECT a FROM Assignment a WHERE a.assgId = :assgId")
    , @NamedQuery(name = "Assignment.findByAname", query = "SELECT a FROM Assignment a WHERE a.aname = :aname")
    , @NamedQuery(name = "Assignment.findByDeadline", query = "SELECT a FROM Assignment a WHERE a.deadline = :deadline")
    , @NamedQuery(name = "Assignment.findByDescription", query = "SELECT a FROM Assignment a WHERE a.description = :description")
    , @NamedQuery(name = "Assignment.findByFilepath", query = "SELECT a FROM Assignment a WHERE a.filepath = :filepath")
    , @NamedQuery(name = "Assignment.findByIssuer", query = "SELECT a FROM Assignment a WHERE a.issuer = :issuer")})
public class Assignment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "assg_id", nullable = false)
    private Integer assgId;
    @Basic(optional = false)
    @Column(name = "aname", nullable = false, length = 45)
    private String aname;
    @Column(name = "deadline")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 500)
    private String description;
    @Basic(optional = false)
    @Column(name = "filepath", nullable = false, length = 200)
    private String filepath;
    @Basic(optional = false)
    @Column(name = "issuer", nullable = false, length = 45)
    private String issuer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assgId")
    private Collection<Submission> submissionCollection;

    public Assignment() {
    }

    public Assignment(Integer assgId) {
        this.assgId = assgId;
    }

    public Assignment(Integer assgId, String aname, String description, String filepath, String issuer) {
        this.assgId = assgId;
        this.aname = aname;
        this.description = description;
        this.filepath = filepath;
        this.issuer = issuer;
    }

    public Integer getAssgId() {
        return assgId;
    }

    public void setAssgId(Integer assgId) {
        this.assgId = assgId;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @XmlTransient
    public Collection<Submission> getSubmissionCollection() {
        return submissionCollection;
    }

    public void setSubmissionCollection(Collection<Submission> submissionCollection) {
        this.submissionCollection = submissionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assgId != null ? assgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assignment)) {
            return false;
        }
        Assignment other = (Assignment) object;
        if ((this.assgId == null && other.assgId != null) || (this.assgId != null && !this.assgId.equals(other.assgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Assignment[ assgId=" + assgId + " ]";
    }
    
}
