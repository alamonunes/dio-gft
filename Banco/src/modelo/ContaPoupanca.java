package modelo;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(Cliente cliente) {
		super(cliente);
	}

	@Override
	public void extrato() {
		System.out.println("\n=== Extrato Conta Poupan�a ===");
		super.imprimirInfosComuns();
	}
}
