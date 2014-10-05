package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author aequites
 */
public class ClientHelperBar extends JPanel implements ActionListener
{
	private final JButton m_jbQLogin = new JButton("Quick Login");
	private final JButton m_jbAdd = new JButton("Add");
	private final JButton m_jbDel = new JButton("Del");
	private final JButton m_jbRole = new JButton("Role");
	private final JButton m_jbTeam = new JButton("Team");
	private final JButton m_jbCompany = new JButton("Company");
    private final JCheckBox m_jchkTC = new JCheckBox("Team Chat");
    
    private final ClientLoginDialog m_dlgQLogin;
    private final ClientAddUserDialog m_dlgAdd;
    private final ClientDelUserDialog m_dlgDel;
    private final ClientSetRoleDialog m_dlgRole;

    private final ClientGUI m_pParent;
    
    public ClientHelperBar(ClientGUI pParent)
    {
        m_pParent = pParent;
        
        m_jbQLogin.addActionListener(this);
        m_jbAdd.addActionListener(this);
        m_jbDel.addActionListener(this);
        m_jbRole.addActionListener(this);
        m_jbTeam.addActionListener(this);
        m_jbCompany.addActionListener(this);
        m_jchkTC.addActionListener(this);
        
        this.add(m_jbQLogin);
        this.add(m_jbAdd);
        this.add(m_jbDel);
        this.add(m_jbRole);
        this.add(m_jbTeam);
        this.add(m_jbCompany);
        this.add(m_jchkTC);

        m_dlgQLogin = new ClientLoginDialog(pParent, true);
        m_dlgAdd = new ClientAddUserDialog(pParent, true);
        m_dlgDel = new ClientDelUserDialog(pParent, true);
        m_dlgRole = new ClientSetRoleDialog(pParent, true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
		Object o = e.getSource();
        JDialog d = null;

        if (!m_pParent.connected)
        {
            if (o == m_jbQLogin) d = m_dlgQLogin;
        }
        else
        {
            if (o == m_jbAdd) d = m_dlgAdd; else
            if (o == m_jbDel) d = m_dlgDel; else
            if (o == m_jbRole) d = m_dlgRole;
        }
        
        if (d != null)
        {
            d.setLocationRelativeTo(m_pParent);
            d.setVisible(true);
        }
    }
    
    public boolean isTeamChatOn()
    {
        return m_jchkTC.isSelected();
    }
}
