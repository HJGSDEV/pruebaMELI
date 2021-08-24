package co.com.personalsoft.pruebaml.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mutant")
public class MutantEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "seq_mutan", sequenceName = "seq_mutant_pk",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mutan")
    @Column(name = "id_mutant")
    private Integer idMutant;
    @Column(name = "adn")
    private String adn;
    @Column(name = "adn_mutant")
    private String adnMutant;
    @Column(name = "mutante")
    private Short mutante;
    @Column(name = "fecha_validacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaValidacion;

    public MutantEntity() {
		// TODO Auto-generated constructor stub
	}

    public MutantEntity(Integer idMutant) {
        this.idMutant = idMutant;
    }

    public Integer getIdMutant() {
        return idMutant;
    }

    public void setIdMutant(Integer idMutant) {
        this.idMutant = idMutant;
    }

    public String getAdn() {
        return adn;
    }

    public void setAdn(String adn) {
        this.adn = adn;
    }

    public String getAdnMutant() {
        return adnMutant;
    }

    public void setAdnMutant(String adnMutant) {
        this.adnMutant = adnMutant;
    }

    public Short getMutante() {
        return mutante;
    }

    public void setMutante(Short mutante) {
        this.mutante = mutante;
    }

    public Date getFechaValidacion() {
        return fechaValidacion;
    }

    public void setFechaValidacion(Date fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMutant != null ? idMutant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MutantEntity)) {
            return false;
        }
        MutantEntity other = (MutantEntity) object;
        if ((this.idMutant == null && other.idMutant != null) || (this.idMutant != null && !this.idMutant.equals(other.idMutant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Mutant[ idMutant=" + idMutant + " ]";
    }
    
}

