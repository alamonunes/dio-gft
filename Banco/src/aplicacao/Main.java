package aplicacao;

import java.util.Scanner;
import fachada.FachadaBanco;

public class Main {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int opcao;

		do {
			menu();
			try {
				opcao = scanner.nextInt();
				
				switch(opcao) {
					case 0: fecharBanco(); break;
					case 1: criarCcorrente(); break;
					case 2: criarCpoupanca(); break;
					case 3: extrato(); break;
					case 4: depositar(); break;
					case 5: sacar(); break;
					case 6: transferir(); break;
					case 7: listarContas(); break;
					case 8: removerConta(); break;
				}
			}catch (Exception e) {
				System.out.println("ERRO: Escolha uma opcao do menu!");
				opcao = 9;
				scanner.next();
			}
		} while(opcao != 0);
	}

	private static void fecharBanco() {
		System.out.println("================= BANCO FECHADO! ==================");
	}

	public static void menu() {
		System.out.println("============= BANCO ABERTO! ==================");
		System.out.println("- - - - - - - - -  Menu  - - - - - - - - - - - ");
		System.out.println("(1) Criar conta corrente");
		System.out.println("(2) Criar conta poupança");
		System.out.println("(3) Extrato da conta");
		System.out.println("(4) Depositar");
		System.out.println("(5) Sacar");
		System.out.println("(6) Transferir");
		System.out.println("(7) Contas Cadastradas");
		System.out.println("(8) Remover conta");
		System.out.println("(0) Sair");
		System.out.print("Opção: ");
	}

	public static void criarCcorrente(){		
		String nome;
		String cpf;

		System.out.print("Digite o seu nome: ");
		nome = scanner.next();

		System.out.print("Digite o seu cpf: ");
		cpf = scanner.next();

		try {
			System.out.println(FachadaBanco.criarContaCorrente(nome, cpf));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void criarCpoupanca(){		
		String nome;
		String cpf;

		System.out.print("Digite o seu nome: ");
		nome = scanner.next();

		System.out.print("Digite o seu cpf: ");
		cpf = scanner.next();

		try {
			System.out.println(FachadaBanco.criarContaPoupanca(nome, cpf));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void extrato() {
		int numero;
		System.out.print("Digite o numero da conta: ");
		numero = scanner.nextInt();
		try{
			FachadaBanco.extratoConta(numero);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void depositar() {
		int numero;
		double valor;
		System.out.print("Digite o numero da conta: ");
		numero = scanner.nextInt();
		System.out.print("Digite o valor a ser depositado: ");
		valor = scanner.nextDouble();

		try {
			System.out.println(FachadaBanco.depositar(numero, valor));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void sacar() {
		int numero;
		double valor;
		System.out.print("Digite o numero da conta: ");
		numero = scanner.nextInt();
		System.out.print("Digite o valor a ser sacado: ");
		valor = scanner.nextDouble();

		try {
			System.out.println(FachadaBanco.sacar(numero, valor));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void transferir() {
		int cOrigem, cDestino;
		double valor;
		System.out.print("Digite o numero da conta de origem: ");
		cOrigem = scanner.nextInt();

		System.out.print("Digite o numero da conta destino: ");
		cDestino = scanner.nextInt();

		System.out.print("Digite o valor a ser transferido: ");
		valor = scanner.nextDouble();

		try {
			System.out.println(FachadaBanco.transferir(cOrigem, cDestino, valor));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void listarContas() {
		System.out.println("\n------------ Contas -----------------");
		FachadaBanco.exibirContas();
		System.out.println("\n");
	}

	private static void removerConta() {
		int numero;
		System.out.print("Digite o numero da conta: ");
		numero = scanner.nextInt();
		try{
			System.out.println(FachadaBanco.fecharConta(numero));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
