/**
 * @author Heider Oliveira - Rafaella Firmino - Alan machado Mendes
 */

package arvore;

import dados.CadastroComputador;

public class Arvore {

	private NoArv raiz;
	private int quantNos;

	public Arvore() {
		this.raiz = null;
		this.quantNos = 0;
	}

	public NoArv getRaiz() {
		return this.raiz;
	}

	public int getQuantNos() {
		return this.quantNos;
	}

	// Evazia
	public boolean eVazia() {
		return (this.raiz == null);
	}

	// Método inserir
	public boolean inserir(CadastroComputador cadastro) {
		if (pesquisar(cadastro.getCodigo())) {
			return false;
		} else {
			this.raiz = inserir(cadastro, this.raiz);
			this.quantNos++;
			return true;
		}
	}

	// Método inserir (lógico)
	private NoArv inserir(CadastroComputador cadastro, NoArv no) {
		if (no == null) {
			NoArv novoNo = new NoArv(cadastro);
			return novoNo;
		} else {
			if (cadastro.getCodigo() < no.getInfo().getCodigo()) {
				no.setEsq(inserir(cadastro, no.getEsq()));
				return no;
			} else {
				no.setDir(inserir(cadastro, no.getDir()));
				return no;
			}
		}
	}

	// Método pesquisar
	public boolean pesquisar(int valor) {
		if (pesquisar(valor, this.raiz) != null) {
			return true;
		} else {
			return false;

		}
	}

	// Método pesquisar (lógico)
	public NoArv pesquisar(int valor, NoArv no) {
		if (no != null) {
			if (valor < no.getInfo().getCodigo()) {
				no = pesquisar(valor, no.getEsq());

			} else {
				if (valor > no.getInfo().getCodigo()) {
					no = pesquisar(valor, no.getDir());
				}
			}
		}
		return no;
	}

	//

	// Método remover cadastro
	public boolean remover(int chave) {
		if (pesquisar(chave, this.raiz) != null) {
			this.raiz = remover(chave, this.raiz);
			this.quantNos--;
			return true;
		} else {
			return false;
		}
	}

	// Método remover cadastro (lógico)
	private NoArv remover(int chave, NoArv arv) {
		if (chave < arv.getInfo().getCodigo()) {
			arv.setEsq(remover(chave, arv.getDir()));
		} else {
			if (chave > arv.getInfo().getCodigo()) {
				arv.setDir(remover(chave, arv.getDir()));
			}

			else {
				if (arv.getDir() == null) {
					return arv.getEsq();
				} else {
					if (arv.getEsq() == null) {
						return arv.getDir();
					} else {
						arv.setEsq(arrumar(arv, arv.getEsq()));
					}
				}
			}
		}

		return arv;

	}

	// Método lógico arrumar após remover um nó que tem dois filhos (lógico)
	private NoArv arrumar(NoArv arv, NoArv maior) {
		if (maior.getDir() != null) {
			maior.setDir(arrumar(arv, maior.getDir()));
		} else {
			arv.setInfo(maior.getInfo());
			maior = maior.getEsq();
		}

		return maior;
	}

	// Método pré-fixado
	public CadastroComputador[] caminhoPreFixado() {
		int[] n = new int[1];
		n[0] = 0;
		CadastroComputador[] vet = new CadastroComputador[this.quantNos];
		return (fazCamPreFixado(this.raiz, vet, n));
	}

	// Método pré-fixado (lógico)
	private CadastroComputador[] fazCamPreFixado(NoArv arv, CadastroComputador[] vet, int[] n) {
		if (arv != null) {
			vet[n[0]] = arv.getInfo();
			n[0]++;
			vet = fazCamPreFixado(arv.getEsq(), vet, n);
			vet = fazCamPreFixado(arv.getDir(), vet, n);
		}
		return vet;
	}

	// Método computadores disponíveis
	public int computadoresDisponiveis() {
		int[] n = new int[1];
		n[0] = 0;
		computadoresDisponiveis(this.raiz, n);
		return n[0];
	}

	// Método computadores disponíveis (lógico)
	private void computadoresDisponiveis(NoArv arv, int[] n) {
		if (arv != null) {
			if (arv.getInfo().isEmUso() == false) {
				n[0]++;
			}
			computadoresDisponiveis(arv.getEsq(), n);
			computadoresDisponiveis(arv.getDir(), n);
		}

	}

	// Método retornar o nome do sistema instalado na máquina
	public String sistemaInstalado(int cadastro) {
		String sist = "";
		NoArv atual = pesquisar(cadastro, this.raiz);
		return sist += atual.getInfo().getSistemaInstalado();
	}
}
