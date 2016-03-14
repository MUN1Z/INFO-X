package br.com.x.telas;

import java.sql.*;
import br.com.x.dal.Conexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 * Usuario.java [Tela] 
 * Classe responsável pela Tela de Usuario do programa
 * @author Felipe Muniz, 2016, INFO-X
 */
public class Usuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form Usuario
     */
    public Usuario() {
        initComponents();
        conexao = Conexao.conector();
    }

    //Método responsavel pela consulta de usuario no banco de dados
    private void read() {

        String sql = "SELECT * FROM usuarios WHERE usuario LIKE ?";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtUsuarioPesquisa.getText() + '%');
            rs = pst.executeQuery();

            tblUsuarios.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    //Método responsavel por setar os campos do usuario com a tabela
    private void setFields() {
        int setar = tblUsuarios.getSelectedRow();
        
        txtUsuarioId.setText(tblUsuarios.getModel().getValueAt(setar, 0).toString());
        txtUsuarioNome.setText(tblUsuarios.getModel().getValueAt(setar, 1).toString());
        txtUsuarioLogin.setText(tblUsuarios.getModel().getValueAt(setar, 2).toString());
        txtUsuarioSenha.setText(tblUsuarios.getModel().getValueAt(setar, 3).toString());
        txtUsuarioFone.setText(tblUsuarios.getModel().getValueAt(setar, 4).toString());
        
        if(tblUsuarios.getModel().getValueAt(setar, 5).toString().equals("1")){
            cboUsuarioNivel.setSelectedItem("Administrador");
        }else if(tblUsuarios.getModel().getValueAt(setar, 5).toString().equals("2")){
             cboUsuarioNivel.setSelectedItem("Usuario");
        }
    }

    //Método responsavel pelo cadastro de usuario no banco de dados
    private void create() {

        String sql = "INSERT INTO usuarios(usuario,fone,login,senha,nivel) VALUES(?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuarioNome.getText());
            pst.setString(2, txtUsuarioFone.getText());
            pst.setString(3, txtUsuarioLogin.getText());
            pst.setString(4, txtUsuarioSenha.getText());

            if (cboUsuarioNivel.getSelectedItem().toString() == "Administrador") {
                pst.setString(5, "1");
            } else {
                pst.setString(5, "2");
            }

            //Verifica se os campos obrigatórios foram preenchidos
            if (txtUsuarioNome.getText().isEmpty() || txtUsuarioLogin.getText().isEmpty() || txtUsuarioSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios! *");
            } else {

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {

                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");

                    //Limpa os campos após o cadastro!
                    txtUsuarioNome.setText("");
                    txtUsuarioLogin.setText("");
                    txtUsuarioSenha.setText("");
                    txtUsuarioFone.setText("");
                    cboUsuarioNivel.setSelectedIndex(0);

                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //Método responsavel pela atualização de usuario no banco de dados
    private void update() {

        String sql = "UPDATE usuarios SET usuario=?,fone=?,login=?,senha=?,nivel=? WHERE id=?";

        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuarioNome.getText());
            pst.setString(2, txtUsuarioFone.getText());
            pst.setString(3, txtUsuarioLogin.getText());
            pst.setString(4, txtUsuarioSenha.getText());

            if (cboUsuarioNivel.getSelectedItem().toString() == "Administrador") {
                pst.setString(5, "1");
            } else {
                pst.setString(5, "2");
            }

            pst.setString(6, txtUsuarioId.getText());

            //Verifica se os campos obrigatórios foram preenchidos
            if (txtUsuarioNome.getText().isEmpty() || txtUsuarioLogin.getText().isEmpty() || txtUsuarioSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios! *");
            } else {

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {

                    JOptionPane.showMessageDialog(null, "Usuário Atualizado com sucesso!");

                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //Método responsavel pela remoção de usuario no banco de dados
    private void delete() {

        int confirmacao = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja deletar o usúario " + txtUsuarioNome.getText() + " ?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {

            String sql = "DELETE FROM usuarios WHERE id = ?";

            try {

                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuarioId.getText());

                int deletado = pst.executeUpdate();

                if (deletado > 0) {

                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");

                    //Limpa os campos após o cadastro!
                    txtUsuarioNome.setText("");
                    txtUsuarioLogin.setText("");
                    txtUsuarioSenha.setText("");
                    txtUsuarioFone.setText("");
                    cboUsuarioNivel.setSelectedIndex(0);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        txtUsuarioNome = new javax.swing.JTextField();
        cboUsuarioNivel = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtUsuarioLogin = new javax.swing.JTextField();
        txtUsuarioSenha = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtUsuarioFone = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnUsuarioCreate = new javax.swing.JButton();
        btnUsuarioDelete = new javax.swing.JButton();
        btnUsuarioUpdate = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtUsuarioId = new javax.swing.JLabel();
        txtUsuarioPesquisa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Usuários");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel2.setText("* Nome");

        txtUsuarioNome.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtUsuarioNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioNomeActionPerformed(evt);
            }
        });

        cboUsuarioNivel.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        cboUsuarioNivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Usuario", "Administrador" }));

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel7.setText("* Login");

        txtUsuarioLogin.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtUsuarioLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioLoginActionPerformed(evt);
            }
        });

        txtUsuarioSenha.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtUsuarioSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioSenhaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel8.setText("* Senha");

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel9.setText("Fone");

        txtUsuarioFone.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        txtUsuarioFone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioFoneActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel10.setText("* Nivel");

        btnUsuarioCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/x/icones/create.png"))); // NOI18N
        btnUsuarioCreate.setToolTipText("Adicionar");
        btnUsuarioCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuarioCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuarioCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioCreateActionPerformed(evt);
            }
        });

        btnUsuarioDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/x/icones/delete.png"))); // NOI18N
        btnUsuarioDelete.setToolTipText("Deletar");
        btnUsuarioDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuarioDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuarioDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioDeleteActionPerformed(evt);
            }
        });

        btnUsuarioUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/x/icones/update.png"))); // NOI18N
        btnUsuarioUpdate.setToolTipText("Alterar");
        btnUsuarioUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnUsuarioUpdate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuarioUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioUpdateActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Campos obrigatórios *");

        txtUsuarioId.setFont(new java.awt.Font("Ubuntu", 0, 1)); // NOI18N

        txtUsuarioPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioPesquisaKeyReleased(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/x/icones/pesquisar.png"))); // NOI18N

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblUsuarios);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsuarioId))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtUsuarioPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel5)
                        .addGap(168, 168, 168)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(9, 9, 9)))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtUsuarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUsuarioSenha)
                                    .addComponent(cboUsuarioNivel, 0, 160, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsuarioFone, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnUsuarioCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsuarioUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsuarioDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtUsuarioPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUsuarioId)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtUsuarioSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuarioFone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(cboUsuarioNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUsuarioUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuarioCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuarioDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setBounds(0, 0, 575, 511);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioNomeActionPerformed

    private void txtUsuarioLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioLoginActionPerformed

    private void txtUsuarioSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioSenhaActionPerformed

    private void txtUsuarioFoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioFoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioFoneActionPerformed

    private void btnUsuarioCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioCreateActionPerformed
        create();
    }//GEN-LAST:event_btnUsuarioCreateActionPerformed

    private void btnUsuarioUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioUpdateActionPerformed
        update();
    }//GEN-LAST:event_btnUsuarioUpdateActionPerformed

    private void btnUsuarioDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioDeleteActionPerformed
        delete();
    }//GEN-LAST:event_btnUsuarioDeleteActionPerformed

    private void txtUsuarioPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioPesquisaKeyReleased
        read();
    }//GEN-LAST:event_txtUsuarioPesquisaKeyReleased

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
       setFields();
    }//GEN-LAST:event_tblUsuariosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuarioCreate;
    private javax.swing.JButton btnUsuarioDelete;
    private javax.swing.JButton btnUsuarioUpdate;
    private javax.swing.JComboBox cboUsuarioNivel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtUsuarioFone;
    private javax.swing.JLabel txtUsuarioId;
    private javax.swing.JTextField txtUsuarioLogin;
    private javax.swing.JTextField txtUsuarioNome;
    private javax.swing.JTextField txtUsuarioPesquisa;
    private javax.swing.JTextField txtUsuarioSenha;
    // End of variables declaration//GEN-END:variables
}
