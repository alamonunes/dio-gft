package repositorio;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Conta;

public class Banco {
	private List<Conta> contas = new ArrayList<Conta>();
	private List<Cliente> clientes = new ArrayList<Cliente>();

	public void adicionarConta(Conta conta) {
		contas.add(conta);
	}

	public void removerConta(Conta conta) {
		contas.remove(conta);
	}

	public void adicionarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public void removerCliente(Cliente cliente) {
		clientes.remove(cliente);
	}

	public Conta localizarConta(int numero) {
		for (Conta conta : contas) {
			if(conta.getNumero() == numero)
				return conta;
		}
		return null;
	}

	public Cliente localizarCliente(String cpf) {
		for (Cliente cliente : clientes) {
			if(cliente.getCpf().equals(cpf))
				return cliente;
		}
		return null;
	}

	public List<Conta> getContas(){
		return contas;
	}

	public List<Cliente> getClientes(){
		return clientes;
	}

}
