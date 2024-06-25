package Programa;

import javax.swing.JOptionPane;

import Utilitarios.Utils;

public class Conta {
	
	private static int contadorDeContas = 1;
	
	private int numeroConta;
	private Cliente cliente;
	private Double saldo = 0.0;
	
	public Conta(Cliente cliente) {
		this.numeroConta = contadorDeContas;
		this.cliente = cliente;
		contadorDeContas += 1;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	public String toString() {
		return"\nNumero da conta: " + this.getNumeroConta()+
			  "\nNome: " + this.cliente.getNome()+
			  "\nCPF: " + this.cliente.getCpf()+
			  "\nEmail: " + this.cliente.getEmail()+
			  "\nSaldo: " + Utils.doubleToString(this.getSaldo())+
			  "\n:"; 
	}	
	
	public void depositar (Double valor) {
		if(valor > 0) {
			setSaldo(getSaldo()+ valor);
			JOptionPane.showMessageDialog(null, "Seu depósito foi realizado com sucesso!");
		}else {
			JOptionPane.showMessageDialog(null, "Não foi possivel realizar o depósito!");
		}
	}

	public void sacar(Double valor) {
		if(valor > 0 && this.getSaldo() >= valor) {
			setSaldo(getSaldo() - valor);
			JOptionPane.showMessageDialog(null,"Saque realizado com sucesso!");
		}else {
			JOptionPane.showMessageDialog(null, "Não foi possovel realizar o saque");
		}
	}
	
	public void transferir(Conta contaParaDeposito, Double valor) {
		if(valor > 0 && this.getSaldo() >= valor) {
			setSaldo(getSaldo() - valor);
			
			contaParaDeposito.saldo = contaParaDeposito.getSaldo() + valor;
			JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
		}else {
			JOptionPane.showMessageDialog(null, "Não foi possivel eralizar a tranferência!");
		}
	}
	
}
