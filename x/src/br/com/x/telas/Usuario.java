package br.com.x.telas;

import java.sql.*;
import br.com.x.dal.Conexao;
import javax.swing.JOptionPane;

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
        String sql = "SELECT * FROM usuarios WHERE usuario = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuarioNome.getText());

            rs = pst.executeQuery();

            if (rs.next()) {

                String id = rs.getString(1);
                String usuario = rs.getString(2);
                String fone = rs.getString(3);
                String login = rs.getString(4);
                String senha = rs.getString(5);
                String nivel = rs.getString(6);

                txtUsuarioId.setText(id);
                txtUsuarioNome.setText(usuario);
                txtUsuarioLogin.setText(login);
                txtUsuarioSenha.setText(senha);
                txtUsuarioFone.setText(fone);

                if (nivel.equals("2")) {
                    cboUsuarioNivel.setSelectedIndex(0);
                } else {
                    cboUsuarioNivel.setSelectedIndex(1);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");

                //Limpa os campos após o erro!
                txtUsuarioId.setText("");
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
        btnUsuarioRead = new javax.swing.JButton();
        btnUsuarioUpdate = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtUsuarioId = new javax.swing.JLabel();
        txtClientePesquisa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();

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

        btnUsuarioRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/x/icones/read.png"))); // NOI18N
        btnUsuarioRead.setToolTipText("Consultar");
        btnUsuarioRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuarioRead.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuarioRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioReadActionPerformed(evt);
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

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/x/icones/pesquisar.png"))); // NOI18N

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUsuarioCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUsuarioDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUsuarioRead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUsuarioUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsuarioId))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtClientePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel5)
                                .addGap(168, 168, 168)
                                .addComponent(jLabel3)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(9, 9, 9)))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsuarioFone, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(214, 214, 214))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtUsuarioNome, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtUsuarioSenha)
                                        .addComponent(cboUsuarioNivel, 0, 160, Short.MAX_VALUE)))
                                .addGap(29, 29, 29))))))
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
                            .addComponent(txtClientePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUsuarioId)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnUsuarioUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuarioRead, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuarioCreate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuarioDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnUsuarioReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioReadActionPerformed
        read();
    }//GEN-LAST:event_btnUsuarioReadActionPerformed

    private void btnUsuarioCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioCreateActionPerformed
        create();
    }//GEN-LAST:event_btnUsuarioCreateActionPerformed

    private void btnUsuarioUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioUpdateActionPerformed
        update();
    }//GEN-LAST:event_btnUsuarioUpdateActionPerformed

    private void btnUsuarioDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioDeleteActionPerformed
        delete();
    }//GEN-LAST:event_btnUsuarioDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuarioCreate;
    private javax.swing.JButton btnUsuarioDelete;
    private javax.swing.JButton btnUsuarioRead;
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
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtClientePesquisa;
    private javax.swing.JTextField txtUsuarioFone;
    private javax.swing.JLabel txtUsuarioId;
    private javax.swing.JTextField txtUsuarioLogin;
    private javax.swing.JTextField txtUsuarioNome;
    private javax.swing.JTextField txtUsuarioSenha;
    // End of variables declaration//GEN-END:variables
}
