/**
 * @author Heider Oliveira - Rafaella Firmino - Alan machado Mendes
 */

package dados;

public class CadastroComputador {

	private int codigo;
	private String sistemaInstalado;
	private int numeroCadeira;
	private boolean emUso;

	public CadastroComputador(int codigo, String sistemaInstalado, int numeroCadeira, boolean emUso) {
		this.codigo = codigo;
		this.sistemaInstalado = sistemaInstalado;
		this.numeroCadeira = numeroCadeira;
		this.emUso = emUso;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getSistemaInstalado() {
		return sistemaInstalado;
	}

	public void setSistemaInstalado(String sistemaInstalado) {
		this.sistemaInstalado = sistemaInstalado;
	}

	public int getNumeroCadeira() {
		return numeroCadeira;
	}

	public void setNumeroCadeira(int numeroCadeira) {
		this.numeroCadeira = numeroCadeira;
	}

	public boolean isEmUso() {
		return emUso;
	}

	public boolean setEmUso(boolean emUso) {
		return this.emUso = emUso;
	}

}
