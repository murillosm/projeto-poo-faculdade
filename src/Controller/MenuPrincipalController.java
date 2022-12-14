package Controller;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

import view.CadastroClienteFrame;
import view.CadastroFornecedorFrame;
import view.MenuPrincipal;
import view.RelatorioVenda;
import view.VendasFrame;
import view.EstoqueFrame;

public class MenuPrincipalController {
	private final MenuPrincipal viewMenuPrincipal;
	private static JDesktopPane desktopPane;

	public MenuPrincipalController(MenuPrincipal viewMenuPrincipal) {
		this.viewMenuPrincipal = viewMenuPrincipal;
	}
	CadastroClienteFrame clientePane = new CadastroClienteFrame();
	CadastroFornecedorFrame fornecedorFrame = new CadastroFornecedorFrame();
	EstoqueFrame produtosFrame = new EstoqueFrame();
	VendasFrame vendasFrame = new VendasFrame();
	RelatorioVenda relatorioVenda = new RelatorioVenda();
	
	
	public void CadastroCliente() {
		desktopPane = MenuPrincipal.getDesktopPane();
		
		if (clientePane.isVisible()) {
			clientePane.toFront();
			clientePane.requestFocus();
			JOptionPane.showMessageDialog(null, "Cadastro de clientes\n já esta em uso", "AVISO",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				if (fornecedorFrame.isVisible()) {
					fornecedorFrame.dispose();
				}
				if (produtosFrame.isVisible()) {
					produtosFrame.dispose();
				}
				desktopPane.add(clientePane);
				desktopPane.moveToFront(clientePane);
				clientePane.setSize(desktopPane.getWidth(), desktopPane.getHeight());
				clientePane.setLocation(0, 0);
				clientePane.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void CadastroFornecedores() {
		desktopPane = MenuPrincipal.getDesktopPane();
		
		if (fornecedorFrame.isVisible()) {
			fornecedorFrame.toFront();
			fornecedorFrame.requestFocus();
			JOptionPane.showMessageDialog(null, "Cadastro de clientes\n já esta em uso", "AVISO",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				if (clientePane.isVisible()) {
					clientePane.dispose();
				}
				if (produtosFrame.isVisible()) {
					produtosFrame.dispose();
				}
				desktopPane.add(fornecedorFrame);
				desktopPane.moveToFront(fornecedorFrame);
				fornecedorFrame.setSize(desktopPane.getWidth(), desktopPane.getHeight());
				fornecedorFrame.setLocation(0, 0);
				fornecedorFrame.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void CadastroProdutos() {
		desktopPane = MenuPrincipal.getDesktopPane();
		
		if (produtosFrame.isVisible()) {
			produtosFrame.toFront();
			produtosFrame.requestFocus();
			JOptionPane.showMessageDialog(null, "Cadastro de clientes\n já esta em uso", "AVISO",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				if (fornecedorFrame.isVisible()) {
					fornecedorFrame.dispose();
				}
				if (clientePane.isVisible()) {
					clientePane.dispose();
				}
				desktopPane.add(produtosFrame);
				desktopPane.moveToFront(produtosFrame);
				produtosFrame.setSize(desktopPane.getWidth(), desktopPane.getHeight());
				produtosFrame.setLocation(0, 0);
				produtosFrame.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void formVendas() {
		desktopPane = MenuPrincipal.getDesktopPane();
		if (vendasFrame.isVisible()) {
			vendasFrame.toFront();
			vendasFrame.requestFocus();
			JOptionPane.showMessageDialog(null, "formulario de vendas\n já esta em uso", "AVISO",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				desktopPane.add(vendasFrame);
				desktopPane.moveToFront(vendasFrame);
				vendasFrame.setSize(desktopPane.getWidth(), desktopPane.getHeight());
				vendasFrame.setLocation(0, 0);
				vendasFrame.setVisible(true);
				vendasFrame.repaint();
				vendasFrame.validate();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void formRelatorios() {
		desktopPane = MenuPrincipal.getDesktopPane();
		if (relatorioVenda.isVisible()) {
			relatorioVenda.toFront();
			relatorioVenda.requestFocus();
			JOptionPane.showMessageDialog(null, "Relatorio de Vendas \n já esta em uso", "AVISO",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				desktopPane.add(relatorioVenda);
				desktopPane.moveToFront(relatorioVenda);
				relatorioVenda.setSize(desktopPane.getWidth(), desktopPane.getHeight());
				relatorioVenda.setLocation(0, 0);
				relatorioVenda.setVisible(true);
				relatorioVenda.repaint();
				relatorioVenda.validate();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
