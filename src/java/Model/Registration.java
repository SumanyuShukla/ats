/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 1396582
 */
@Entity
@Table(name = "registration", catalog = "ats_hib", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registration.findAll", query = "SELECT r FROM Registration r")
    , @NamedQuery(name = "Registration.findByRegId", query = "SELECT r FROM Registration r WHERE r.regId = :regId")
    , @NamedQuery(name = "Registration.findByUsername", query = "SELECT r FROM Registration r WHERE r.username = :username")
    , @NamedQuery(name = "Registration.findByEmail", query = "SELECT r FROM Registration r WHERE r.email = :email")
    , @NamedQuery(name = "Registration.findByPassword", query = "SELECT r FROM Registration r WHERE r.password = :password")
    , @NamedQuery(name = "Registration.findByUsertype", query = "SELECT r FROM Registration r WHERE r.usertype = :usertype")})
public class Registration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reg_id", nullable = false)
    private Integer regId;
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 45)
    private String email;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 45)
    private String password;
    @Basic(optional = false)
    @Column(name = "usertype", nullable = false, length = 45)
    private String usertype;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regId")
    private Collection<Submission> submissionCollection;

    public Registration() {
    }

    public Registration(Integer regId) {
        this.regId = regId;
    }

    public Registration(Integer regId, String username, String email, String password, String usertype) {
        this.regId = regId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.usertype = usertype;
    }

    public Integer getRegId() {
        return regId;
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
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
        hash += (regId != null ? regId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registration)) {
            return false;
        }
        Registration other = (Registration) object;
        if ((this.regId == null && other.regId != null) || (this.regId != null && !this.regId.equals(other.regId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Registration[ regId=" + regId + " ]";
    }
    
}
