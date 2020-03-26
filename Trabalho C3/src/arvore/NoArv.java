/**
 * @author Heider Oliveira - Rafaella Firmino - Alan machado Mendes
 */

package arvore;

import dados.CadastroComputador;

public class NoArv {

	private CadastroComputador info;
	private NoArv dir, esq;

	public NoArv(CadastroComputador elem) {
		this.info = elem;
		this.dir = null;
		this.esq = null;
	}

	public CadastroComputador getInfo() {
		return this.info;
	}

	public void setInfo(CadastroComputador elem) {
		this.info = elem;
	}

	public NoArv getDir() {
		return this.dir;
	}

	public void setDir(NoArv no) {
		this.dir = no;
	}

	public NoArv getEsq() {
		return this.esq;
	}

	public void setEsq(NoArv no) {
		this.esq = no;
	}

}
