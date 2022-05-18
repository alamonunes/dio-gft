package fachada;

import modelo.Cliente;
import modelo.Conta;
import modelo.ContaCorrente;
import modelo.ContaPoupanca;
import repositorio.Banco;

public class FachadaBanco {
	private static Banco banco = new Banco();


	public static String criarContaCorrente(String nome, String cpf) throws Exception{
		Cliente cliente = banco.localizarCliente(cpf);
		if(cliente != null)
			throw new Exception("Cliente ja possui conta.");

		if(!nome.matches("^[a-zA-Z]*$"))
			throw new Exception("ERRO: Nome invalido");

		if(!cpf.matches("[0-9]*$"))
			throw new Exception("ERRO: CPF invalido");

		cliente = new Cliente(nome, cpf);
		Conta conta = new ContaCorrente(cliente);
		banco.adicionarConta(conta);
		banco.adicionarCliente(cliente);
		return "Conta corrente criada com sucesso! Numero: " + conta.getNumero();
	}

	public static String criarContaPoupanca(String nome, String cpf) throws Exception{
		Cliente cliente = banco.localizarCliente(cpf);
		if(cliente != null)
			throw new Exception("Cliente ja possui conta.");

		if(!nome.matches("^[a-zA-Z]*$"))
			throw new Exception("ERRO: Nome invalido");

		if(!cpf.matches("[0-9]*$"))
			throw new Exception("ERRO: CPF invalido");

		cliente = new Cliente(nome, cpf);
		Conta conta = new ContaPoupanca(cliente);
		banco.adicionarCliente(cliente);
		banco.adicionarConta(conta);
		return "Conta poupança criada com sucesso! Numero: " + conta.getNumero();
	}

	public static String sacar(int numero, double valor) throws Exception{
		Conta conta = banco.localizarConta(numero);
		if(conta == null)
			throw new Exception("Conta inexistente.");
		if(conta.getSaldo() < valor)
			throw new Exception("Erro no saque. Saldo insuficiente.");
		if(valor <= 0)
			throw new Exception("Valor não permitido.");

		conta.sacar(valor);
		return "Valor " + valor + " extraido da conta " + numero + ". Saldo: R$" + conta.getSaldo();
	}

	public static String depositar(int numero, double valor) throws Exception{
		Conta conta = banco.localizarConta(numero);
		if(conta == null)
			throw new Exception("Conta " + numero + " inexistente.");
		if(valor <= 0)
			throw new Exception("Valor não permitido.");

		conta.depositar(valor);
		return "Valor R$" + valor + " depositado na conta " + numero + ". Saldo: R$" + conta.getSaldo();
	}

	public static String transferir(int contaOrigem, int contaDestino, double valor) throws Exception{
		Conta contaO = banco.localizarConta(contaOrigem);
		Conta contaD = banco.localizarConta(contaDestino);
		if(contaO == null)
			throw new Exception("Conta " + contaOrigem + " inexistente.");
		if(contaD == null)
			throw new Exception("Conta " + contaDestino + " inexistente.");

		if(contaO == contaD)
			throw new Exception("Não é possivel transferir para a mesma conta.");

		if(valor <= 0)
			throw new Exception("Valor não aceito.");

		if(contaO.getSaldo() < valor)
			throw new Exception("Saldo da conta " + contaOrigem + " insuficiente.");

		contaO.transferir(valor, contaD);
		return "Valor R$" + valor + " transferido para a conta " + contaDestino;
	}

	public static void extratoConta(int numero) throws Exception{
		Conta conta = banco.localizarConta(numero);
		if(conta == null)
			throw new Exception("Conta " + numero + " inexistente.");

		conta.extrato();
	}

	public static String fecharConta(int numero) throws Exception{
		Conta conta = banco.localizarConta(numero);
		if(conta == null)
			throw new Exception("Conta " + numero + " inexistente.");

		banco.removerCliente(conta.getCliente());
		banco.removerConta(conta);
		return "Conta " + numero + " fechada.";
	}

	public static void exibirContas(){
		for (Conta conta : banco.getContas()) {
			conta.extrato();
		}
	}

	public static void exibirClientes(){
		for (Cliente cliente : banco.getClientes()) {
			System.out.println(cliente.toString());
		}
	}
}
