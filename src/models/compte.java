package models;


public class compte {

int num_compte;
double solde;
double decouvertMax;
double debitMax;
String situation_compte;
int idClient;



public compte() {
	super();
	// TODO Auto-generated constructor stub
}

public compte(int num_compte, double solde, double decouvertMax, double debitMax, String situation_compte,
		int idClient) {
	super();
	this.num_compte = num_compte;
	this.solde = solde;
	this.decouvertMax = decouvertMax;
	this.debitMax = debitMax;
	this.situation_compte = situation_compte;
	this.idClient = idClient;
}

public int getNum_compte() {
	return num_compte;
}
public void setNum_compte(int num_compte) {
	this.num_compte = num_compte;
}
public double getSolde() {
	return solde;
}
public void setSolde(double solde) {
	this.solde = solde;
}
public double getDecouvertMax() {
	return decouvertMax;
}
public void setDecouvertMax(double decouvertMax) {
	this.decouvertMax = decouvertMax;
}
public double getDebitMax() {
	return debitMax;
}
public void setDebitMax(double debitMax) {
	this.debitMax = debitMax;
}
public String getSituation_compte() {
	return situation_compte;
}
public void setSituation_compte(String situation_compte) {
	this.situation_compte = situation_compte;
}
public int getIdClient() {
	return idClient;
}
public void setIdClient(int idClient) {
	this.idClient = idClient;
}



}
