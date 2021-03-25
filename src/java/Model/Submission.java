/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 1396582
 */
@Entity
@Table(catalog = "ats_hib", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Submission.findAll", query = "SELECT s FROM Submission s")
    , @NamedQuery(name = "Submission.findBySubId", query = "SELECT s FROM Submission s WHERE s.subId = :subId")
    , @NamedQuery(name = "Submission.findByFilepath", query = "SELECT s FROM Submission s WHERE s.filepath = :filepath")})
public class Submission implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sub_id", nullable = false)
    private Integer subId;
    @Basic(optional = false)
    @Column(nullable = false, length = 200)
    private String filepath;
    @JoinColumn(name = "reg_id", referencedColumnName = "reg_id", nullable = false)
    @ManyToOne(optional = false)
    private Registration regId;
    @JoinColumn(name = "assg_id", referencedColumnName = "assg_id", nullable = false)
    @ManyToOne(optional = false)
    private Assignment assgId;

    public Submission() {
    }

    public Submission(Integer subId) {
        this.subId = subId;
    }

    public Submission(Integer subId, String filepath) {
        this.subId = subId;
        this.filepath = filepath;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Registration getRegId() {
        return regId;
    }

    public void setRegId(Registration regId) {
        this.regId = regId;
    }

    public Assignment getAssgId() {
        return assgId;
    }

    public void setAssgId(Assignment assgId) {
        this.assgId = assgId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subId != null ? subId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Submission)) {
            return false;
        }
        Submission other = (Submission) object;
        if ((this.subId == null && other.subId != null) || (this.subId != null && !this.subId.equals(other.subId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Submission[ subId=" + subId + " ]";
    }
    
}
