/*

 *
 *  This file is part of BrewPlus.
 *
 *  BrewPlus is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  BrewPlus is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with BrewPlus; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package jmash;

/**
 *
 * @author  Alessandro
 */
public class DimensionamentoControflusso extends javax.swing.JInternalFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1806368273206323875L;
	/** Creates new form DimensionamentoControflusso */
    public DimensionamentoControflusso() {
        initComponents();
        this.cMosto.setModelFormat(0.95*4178.0, 0,100000,1,"0","DimensionamentoControflusso.cMosto");
        this.cRete.setModelFormat(4178.0, 0,100000,1,"0","DimensionamentoControflusso.cRete");
        this.dMosto.setModelFormat(1.050, 0,2,0.01,"0.000","DimensionamentoControflusso.dMosto");
        this.dRete.setModelFormat(1, 0,1000,0.01,"0.000","DimensionamentoControflusso.dRete");
        this.diTubo.setModelFormat(0.008, 0,1000,0.001,"0.0000","DimensionamentoControflusso.diTubo");
        this.deTubo.setModelFormat(0.010, 0,1000,0.001,"0.0000","DimensionamentoControflusso.deTubo");
        this.di2Tubo.setModelFormat(0.012, 0,1000,0.001,"0.0000","DimensionamentoControflusso.di2Tubo");

        this.kMosto.setModelFormat(0.625*1.1, 0,1000,0.001,"0.0000","DimensionamentoControflusso.kMosto");
        this.kRete.setModelFormat(0.625, 0,1000,0.001,"0.0000","DimensionamentoControflusso.kRete");
        this.kTubo.setModelFormat(380, 0,1000,1.0,"0.0","DimensionamentoControflusso.kTubo");
        this.muMosto.setModelFormat((1.15/1.002)*0.75/1000, 0,1000,0.0001,"0.00000","DimensionamentoControflusso.muMosto");
        this.muRete.setModelFormat(0.75/1000, 0,1000,0.0001,"0.00000","DimensionamentoControflusso.muRete");
        this.tMostoFin.setModelFormat(21, 0,100,1,"0.0","DimensionamentoControflusso.tMostoFin");
        this.tMostoIni.setModelFormat(100, 0,100,1,"0.0","DimensionamentoControflusso.tMostoIni");
        this.tRete.setModelFormat(18, 0,100,1,"0.0","DimensionamentoControflusso.tRete");
        this.fMosto.setModelFormat(3, 0.1,100,0.5,"0.0","DimensionamentoControflusso.fMosto");
        this.fRete.setModelFormat(3, 0.1,100,0.5,"0.0","DimensionamentoControflusso.fRete");
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        calc();
        jLabel22.setText("Densità");
        jLabel18.setText("Densità");
    }
 
    
    private void calc(){
    
       double Gm=this.dMosto.getDoubleValue()*this.fMosto.getDoubleValue()/60;
       double Ga=this.dRete.getDoubleValue()*this.fRete.getDoubleValue()*this.fMosto.getDoubleValue()/60;
       double Q=Gm*this.cMosto.getDoubleValue()*(this.tMostoIni.getDoubleValue()-this.tMostoFin.getDoubleValue());
       
       double T=this.tRete.getDoubleValue()+Q/(Ga*this.cRete.getDoubleValue());
       
       double AT=((this.tMostoIni.getDoubleValue()-T)-(this.tMostoFin.getDoubleValue()-this.tRete.getDoubleValue()))/
                    Math.log((this.tMostoIni.getDoubleValue()-T)/(this.tMostoFin.getDoubleValue()-this.tRete.getDoubleValue()));
       
       double Prm=this.muMosto.getDoubleValue()*this.cMosto.getDoubleValue()/this.kMosto.getDoubleValue();
       double Pra=this.muRete.getDoubleValue()*this.cRete.getDoubleValue()/this.kRete.getDoubleValue();
       
       double Wm=Gm / (1000*this.dMosto.getDoubleValue() * Math.PI * this.diTubo.getDoubleValue() * this.diTubo.getDoubleValue() /4 );
       
       double Rem=1000*this.dMosto.getDoubleValue()*Wm*this.diTubo.getDoubleValue()/this.muMosto.getDoubleValue();
       double Num=Rem>10000?
                    0.023*Math.pow(Rem,0.8)*Math.pow(Prm,0.3):
                    0.0033*Rem*Math.pow(Prm,0.37);
       
       double h1= Num * this.kMosto.getDoubleValue() / this.diTubo.getDoubleValue();

       double Wa=Ga / (1000*this.dRete.getDoubleValue() * Math.PI * (-this.deTubo.getDoubleValue() * this.deTubo.getDoubleValue()+this.di2Tubo.getDoubleValue() * this.di2Tubo.getDoubleValue()) /4 );
       
       double Deq = (-this.deTubo.getDoubleValue() * this.deTubo.getDoubleValue()+this.di2Tubo.getDoubleValue() * this.di2Tubo.getDoubleValue())/(this.deTubo.getDoubleValue()+this.di2Tubo.getDoubleValue());
       
       double Rea=1000*this.dRete.getDoubleValue()*Wa*Deq/this.muRete.getDoubleValue();
       double Nua=Rea>10000?
                    0.023*Math.pow(Rea,0.8)*Math.pow(Pra,0.3):
                    0.0033*Rea*Math.pow(Pra,0.37);
       
       double h2= Nua * this.kRete.getDoubleValue() / Deq;

       double K = 1/(
                    1/h1 + 
                    (1/h2)*(this.diTubo.getDoubleValue()/this.di2Tubo.getDoubleValue()) +
                    Math.log(this.deTubo.getDoubleValue()/this.diTubo.getDoubleValue())*this.diTubo.getDoubleValue()/(this.kTubo.getDoubleValue()*2)
                    );
       double L = Q / (K * Math.PI * this.diTubo.getDoubleValue() * AT);
       String str=
              "Energia scambiata [W]       = "+Q;
       str+="\nT acqua uscita [°C]         = "+T;
       str+="\nAT medio                    = "+AT;
       str+="\nVelocità  mosto [Kg/s]       = "+Gm;
       str+="\nVelocità  acqua [Kg/s]       = "+Ga;
       str+="\nNum. di Prandl mosto        = "+Prm;
       str+="\nNum. di Prandl acqua        = "+Pra;
       str+="\nVelocità  mosto parete [m/s] = "+Wm;
       str+="\nNum. Reynolds mosto         = "+Rem;
       str+="\nNum. Nussel mosto           = "+Num;
       str+="\ncoeff. scambio mosto/tubo   = "+h1;
       str+="\nVelocità  acqua parete [m/s] = "+Wa;
       str+="\nDiam.equiv. corona est. [m] = "+Deq;
       str+="\nNum. Reynolds acqua         = "+Rea;
       str+="\nNum. Nussel acqua           = "+Nua;
       str+="\ncoeff. scambio tubo/acqua   = "+h2;
       str+="\ncoeff. globale di scambio   = "+K;
       str+="\nLunghezza richiesta [m]     = "+L;
       this.txt.setText(str);
               
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tMostoFin = new jmash.component.JMashSpinner();
        tMostoIni = new jmash.component.JMashSpinner();
        jLabel3 = new javax.swing.JLabel();
        tRete = new jmash.component.JMashSpinner();
        jLabel16 = new javax.swing.JLabel();
        fMosto = new jmash.component.JMashSpinner();
        jLabel17 = new javax.swing.JLabel();
        fRete = new jmash.component.JMashSpinner();
        jPanel4 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        dRete = new jmash.component.JMashSpinner();
        jLabel23 = new javax.swing.JLabel();
        cRete = new jmash.component.JMashSpinner();
        jLabel24 = new javax.swing.JLabel();
        muRete = new jmash.component.JMashSpinner();
        jLabel25 = new javax.swing.JLabel();
        kRete = new jmash.component.JMashSpinner();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        dMosto = new jmash.component.JMashSpinner();
        jLabel19 = new javax.swing.JLabel();
        cMosto = new jmash.component.JMashSpinner();
        jLabel20 = new javax.swing.JLabel();
        muMosto = new jmash.component.JMashSpinner();
        jLabel21 = new javax.swing.JLabel();
        kMosto = new jmash.component.JMashSpinner();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        kTubo = new jmash.component.JMashSpinner();
        diTubo = new jmash.component.JMashSpinner();
        deTubo = new jmash.component.JMashSpinner();
        di2Tubo = new jmash.component.JMashSpinner();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Dimensionamento controflusso");
        setFont(getFont());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Dati primari"));

        jLabel1.setText("T mosto iniziale");

        jLabel2.setText("T mosto finale");

        tMostoFin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tMostoFinStateChanged(evt);
            }
        });

        tMostoIni.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tMostoIniStateChanged(evt);
            }
        });

        jLabel3.setText("T acqua rete");

        tRete.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tReteStateChanged(evt);
            }
        });

        jLabel16.setText("Mosto (L/min)");

        fMosto.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fMostoStateChanged(evt);
            }
        });

        jLabel17.setText("Acqua/mosto");

        fRete.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fReteStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel1)
                            .add(jLabel2)
                            .add(jLabel3))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED))
                    .add(jLabel16)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jLabel17)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, fRete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, tMostoIni, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, tMostoFin, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, tRete, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, fMosto, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(tMostoIni, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tMostoFin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tRete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(fMosto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel16))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(fRete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel17)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Dati acqua rete"));

        jLabel22.setText("runtime");

        dRete.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dReteStateChanged(evt);
            }
        });

        jLabel23.setText("Cap.term.");

        cRete.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cReteStateChanged(evt);
            }
        });

        jLabel24.setText("Visc.din.");

        muRete.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                muReteStateChanged(evt);
            }
        });

        jLabel25.setText("Conduttiv.");

        kRete.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                kReteStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel25)
                    .add(jLabel24)
                    .add(jLabel22)
                    .add(jLabel23))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cRete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dRete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(muRete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(kRete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel22)
                    .add(dRete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cRete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel23))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel24)
                    .add(muRete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel25)
                    .add(kRete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Dati mosto"));

        jLabel18.setText("runtime");

        dMosto.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dMostoStateChanged(evt);
            }
        });

        jLabel19.setText("Cap.term.");

        cMosto.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cMostoStateChanged(evt);
            }
        });

        jLabel20.setText("Visc.din.");

        muMosto.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                muMostoStateChanged(evt);
            }
        });

        jLabel21.setText("Conduttiv.");

        kMosto.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                kMostoStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel21)
                    .add(jLabel20)
                    .add(jLabel18)
                    .add(jLabel19))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cMosto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dMosto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(muMosto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(kMosto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel18)
                    .add(dMosto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cMosto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel19))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel20)
                    .add(muMosto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel21)
                    .add(kMosto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dati tubo"));

        jLabel12.setText("Conduttiv. materiale");

        jLabel13.setText("Diametro interno tubo interno");

        jLabel14.setText("Diametro esterno tubo interno");

        kTubo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                kTuboStateChanged(evt);
            }
        });

        diTubo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                diTuboStateChanged(evt);
            }
        });

        deTubo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                deTuboStateChanged(evt);
            }
        });

        di2Tubo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                di2TuboStateChanged(evt);
            }
        });

        jLabel15.setText("Diametro interno tubo esterno");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(jLabel15)
                        .add(jLabel13)
                        .add(jLabel12))
                    .add(jLabel14))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(diTubo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .add(kTubo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .add(di2Tubo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .add(deTubo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(kTubo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel12))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(diTubo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel13))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(deTubo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel14))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(di2Tubo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel15))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt.setColumns(20);
        txt.setEditable(false);
        txt.setFont(new java.awt.Font("Courier", 0, 11));
        txt.setRows(5);
        jScrollPane1.setViewportView(txt);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jmash/images/chiller_big.png.gif"))); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)))
                .add(11, 11, 11))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 207, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fReteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fReteStateChanged
calc();
    }//GEN-LAST:event_fReteStateChanged

    private void fMostoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fMostoStateChanged
calc();
    }//GEN-LAST:event_fMostoStateChanged

    private void di2TuboStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_di2TuboStateChanged
calc();
    }//GEN-LAST:event_di2TuboStateChanged

    private void deTuboStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_deTuboStateChanged
calc();
    }//GEN-LAST:event_deTuboStateChanged

    private void diTuboStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_diTuboStateChanged
calc();
    }//GEN-LAST:event_diTuboStateChanged

    private void kTuboStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_kTuboStateChanged
calc();
    }//GEN-LAST:event_kTuboStateChanged

    private void kMostoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_kMostoStateChanged
calc();
    }//GEN-LAST:event_kMostoStateChanged

    private void muMostoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_muMostoStateChanged
calc();
    }//GEN-LAST:event_muMostoStateChanged

    private void cMostoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cMostoStateChanged
calc();
    }//GEN-LAST:event_cMostoStateChanged

    private void dMostoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dMostoStateChanged
calc();
    }//GEN-LAST:event_dMostoStateChanged

    private void kReteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_kReteStateChanged
calc();
    }//GEN-LAST:event_kReteStateChanged

    private void muReteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_muReteStateChanged
calc();
    }//GEN-LAST:event_muReteStateChanged

    private void cReteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cReteStateChanged
calc();
    }//GEN-LAST:event_cReteStateChanged

    private void dReteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dReteStateChanged
calc();
    }//GEN-LAST:event_dReteStateChanged

    private void tReteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tReteStateChanged
calc();
    }//GEN-LAST:event_tReteStateChanged

    private void tMostoFinStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tMostoFinStateChanged
calc();
    }//GEN-LAST:event_tMostoFinStateChanged

    private void tMostoIniStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tMostoIniStateChanged
calc();
    }//GEN-LAST:event_tMostoIniStateChanged
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private jmash.component.JMashSpinner cMosto;
    private jmash.component.JMashSpinner cRete;
    private jmash.component.JMashSpinner dMosto;
    private jmash.component.JMashSpinner dRete;
    private jmash.component.JMashSpinner deTubo;
    private jmash.component.JMashSpinner di2Tubo;
    private jmash.component.JMashSpinner diTubo;
    private jmash.component.JMashSpinner fMosto;
    private jmash.component.JMashSpinner fRete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private jmash.component.JMashSpinner kMosto;
    private jmash.component.JMashSpinner kRete;
    private jmash.component.JMashSpinner kTubo;
    private jmash.component.JMashSpinner muMosto;
    private jmash.component.JMashSpinner muRete;
    private jmash.component.JMashSpinner tMostoFin;
    private jmash.component.JMashSpinner tMostoIni;
    private jmash.component.JMashSpinner tRete;
    private javax.swing.JTextArea txt;
    // End of variables declaration//GEN-END:variables
    
}
