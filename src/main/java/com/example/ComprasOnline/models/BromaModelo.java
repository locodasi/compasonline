package com.example.ComprasOnline.models;

public class BromaModelo {

	private String setup;
	
	private String punchline;

	public BromaModelo() {}
	
	public BromaModelo(String setup, String punchline) {
		super();
		this.setup = setup;
		this.punchline = punchline;
	}

	public String getSetup() {
		return setup;
	}

	public void setSetup(String setup) {
		this.setup = setup;
	}

	public String getPunchline() {
		return punchline;
	}

	public void setPunchline(String punchline) {
		this.punchline = punchline;
	}

	@Override
	public String toString() {
		return "BromaModelo [setup=" + setup + ", punchline=" + punchline + "]";
	}
	
	
}
