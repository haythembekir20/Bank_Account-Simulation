package models;

public class Client {

int idClient;
String NomCli;
String PrenomCli;
String adrCli;



public Client() {
	super();
	// TODO Auto-generated constructor stub
}
public Client(int idClient, String nomCli, String prenomCli, String adrCli) {
	super();
	this.idClient = idClient;
	NomCli = nomCli;
	PrenomCli = prenomCli;
	this.adrCli = adrCli;
	
}
public int getIdClient() {
	return idClient;
}
public void setIdClient(int idClient) {
	this.idClient = idClient;
}
public String getNomCli() {
	return NomCli;
}
public void setNomCli(String nomCli) {
	NomCli = nomCli;
}
public String getPrenomCli() {
	return PrenomCli;
}
public void setPrenomCli(String prenomCli) {
	PrenomCli = prenomCli;
}
public String getAdrCli() {
	return adrCli;
}
public void setAdrCli(String adrCli) {
	this.adrCli = adrCli;
}



}
