/**
 * @author Heider Oliveira - Rafaella Firmino - Alan machado Mendes 
 */

package arvore;

import java.util.Scanner;

import dados.CadastroComputador;

public class Aplicativo {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		Arvore cadastroPc = new Arvore();
		CadastroComputador[] vetor = new CadastroComputador[cadastroPc.getQuantNos()];

		int valor = 0;
		String sistema = "";
		int numCadeira = 0;
		String estadoPc = "";
		boolean emUso = false;

		char opcao;

		do {

			System.out.println("Menu\n" + " 1. Cadastrar\n" + " 2. Excluir\n" + " 3. Consultar Cadastro\n "
					+ "4. Exibir Cadastro Pré-Fixado\n" + " 5. Alterar Cadastro\n"
					+ " 6. Consultar Computadores Livres\n " + "7. Consultar Sistema Instalado \n"
					+ " 9. Sair do Sistema.\n");

			opcao = scan.next().charAt(0);

			switch (opcao) {
			case '1':
				System.out.println("Informe o código do computador: ");
				valor = scan.nextInt();

				System.out.println("Informe o sistema operacional instalado no computador: ");
				sistema = scan.next();

				System.out.println("Informe o número da cadeira do computador: ");
				numCadeira = scan.nextInt();

				System.out.println("Computador está em uso: ");
				estadoPc = scan.next();

				cadastroPc.inserir(new CadastroComputador(valor, sistema, numCadeira, emUso));

				if (estadoPc.equals("s") || estadoPc.equals("S")) {
					emUso = cadastroPc.getRaiz().getInfo().setEmUso(true);
				} else {
					if (estadoPc.equals("n") || estadoPc.equals("N")) {
						emUso = cadastroPc.getRaiz().getInfo().setEmUso(false);
					}
				}

				break;

			case '2':
				if (cadastroPc.eVazia()) {
					System.err.println("Não há cadastro no sistema.\n");
				} else {
					System.out.println("Informe o código de cadastro do PC a ser removido: ");
					valor = scan.nextInt();
					if (cadastroPc.pesquisar(valor)) {
						cadastroPc.remover(valor);
						System.out.println("Valor " + valor + " foi removido do sistema com sucesso.");
					} else {
						System.err.println("O valor " + valor + " Não foi localizado.\n");
					}
				}
				break;

			case '3':
				if (cadastroPc.eVazia()) {
					System.err.println("Não há cadastro no sistema.\n");
				} else {
					System.out.println("Informe o número do cadastro: ");
					valor = scan.nextInt();
					if (cadastroPc.pesquisar(valor)) {
						System.out.println("O valor " + valor + " está cadastrado no sistema.");
					} else {
						System.err.println("O valor " + valor + " não foi localizado no sistema.");
					}
				}
				break;

			case '4':
				if (cadastroPc.eVazia()) {
					System.err.println("Não há cadastro no sistema.\n");
				} else {
					vetor = cadastroPc.caminhoPreFixado();
					String msg = "";
					for (int i = 0; i < cadastroPc.getQuantNos(); i++) {
						msg += "  Número do cadasto:" + vetor[i].getCodigo() + "|" + " Está ligado: "
								+ vetor[i].isEmUso() + "|" + " Sistema instalado:" + vetor[i].getSistemaInstalado()
								+ "|" + " Número da cadeira:" + vetor[i].getNumeroCadeira() + "\n";
					}
					System.out.println("Dados no sistema pré-ordenados:\n" + msg);
				}
				break;

			case '5':
				if (cadastroPc.eVazia()) {
					System.err.println("Não há cadastro no sistema.\n");
				} else {
					System.out.println("Informe o código do computador a ser alterado: ");
					valor = scan.nextInt();
					NoArv atual = cadastroPc.pesquisar(valor, cadastroPc.getRaiz());
					if (atual != null) {
						char opcao2;

						do {
							System.out.println("Quais dados deseja alterar\n" + " 1. Sistema instalado\n"
									+ " 2. Número da cadeira\n " + "3. Estado do computador\n"
									+ " 8. Concluir alteração.\n");

							opcao2 = scan.next().charAt(0);

							switch (opcao2) {
							case '1':
								System.out.println("Informe o novo sistema operacional: ");
								sistema = scan.next();
								atual.getInfo().setSistemaInstalado(sistema);
								System.out.println("Alterado com sucesso!\n");
								break;

							case '2':
								System.out.println("Informe o novo número da cadeira do computador: ");
								numCadeira = scan.nextInt();
								atual.getInfo().setNumeroCadeira(numCadeira);
								System.out.println("Alterado com sucesso!\n");
								break;

							case '3':
								System.out.println("Computador em uso: ");
								estadoPc = scan.next();

								if (estadoPc.equals("s") || estadoPc.equals("S")) {
									emUso = atual.getInfo().setEmUso(true);
									System.out.println("Alterado com sucesso!\n");
								} else {
									if (estadoPc.equals("n") || estadoPc.equals("N")) {
										emUso = atual.getInfo().setEmUso(false);
										System.out.println("Alterado com sucesso!\n");
									}
								}
								break;

							}
						} while (opcao2 != '8');

					} else {
						System.err.println("Valor não encontrado no sistema.");
					}
				}
				break;

			case '6':
				int pcDisponivel = cadastroPc.computadoresDisponiveis();
				if (cadastroPc.eVazia()) {
					System.err.println("Não há cadastro de computadores neste sistema.\n");
				} else {
					if (pcDisponivel == 0) {
						System.err.println("Todos os computadores estão ocupados no momento.\n");
					} else {
						if (pcDisponivel == 1) {
							System.out.println("Há apenas " + pcDisponivel + " computador disponível no momento.\n");
						} else {
							System.out.println("Há " + pcDisponivel + " computadores disponíveis no momento.\n");
						}
					}
				}
				break;

			case '7':
				if (cadastroPc.eVazia()) {
					System.err.println("Não há computadores cadastrados no sistema.\n");
				} else {
					System.out.println("Informe o código do computador:");
					valor = scan.nextInt();
					if (!(cadastroPc.pesquisar(valor))) {
						System.err.println("Máquina não localizada com este número de cadastro.");
					} else {
						System.out.println("Nome do sistema operacional instalado nessa máquina: "
								+ cadastroPc.sistemaInstalado(valor) + "\n");
					}
				}
				break;
			}

		} while (opcao != '9');
		System.out.println("FIM");
		System.exit(0);

	}

}
