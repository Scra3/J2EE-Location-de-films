package Classes;


import java.math.BigDecimal;

/**
 * Client generated by hbm2java
 */
public class Client  implements java.io.Serializable {


     private BigDecimal idmembre;
     private Membre membre;
     private Forfait forfait;
     private Infocredit infocredit;

    public Client() {
    }

	
    public Client(Membre membre) {
        this.membre = membre;
    }
    public Client(Membre membre, Forfait forfait, Infocredit infocredit) {
       this.membre = membre;
       this.forfait = forfait;
       this.infocredit = infocredit;
    }
   
    public BigDecimal getIdmembre() {
        return this.idmembre;
    }
    
    public void setIdmembre(BigDecimal idmembre) {
        this.idmembre = idmembre;
    }
    public Membre getMembre() {
        return this.membre;
    }
    
    public void setMembre(Membre membre) {
        this.membre = membre;
    }
    public Forfait getForfait() {
        return this.forfait;
    }
    
    public void setForfait(Forfait forfait) {
        this.forfait = forfait;
    }
    public Infocredit getInfocredit() {
        return this.infocredit;
    }
    
    public void setInfocredit(Infocredit infocredit) {
        this.infocredit = infocredit;
    }




}

