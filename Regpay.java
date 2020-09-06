import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;

/*
<applet code = "Regpay" width = 400  height = 400>
</applet>
*/

public class Regpay extends JApplet implements ActionListener{
	JTextField amounttext, paymenttext, periodtext, ratetext;
	JButton doIt;
	
	double principal;
	double intrate;
	double numyears;
	double result;
	
	NumberFormat nf;
	
	public void init() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					makeGUI();
				}
			
			});
			
		}catch(Exception ex) {
			System.out.println("shit went wrong"+ ex);
		}
	}
	
	
	private void makeGUI() {
		GridBagLayout gbag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gbag);
		
		JLabel heading = new JLabel("compute Monthly loan payment");
		JLabel amountLab = new JLabel("principal");
		JLabel periodLab = new JLabel("years");
		JLabel rateLab = new JLabel("Interest Rate");
		JLabel paymentLab = new JLabel ("monthly Payment");
		
		amounttext = new JTextField(10);
		periodtext = new JTextField(10);
		paymenttext = new JTextField(10);
		ratetext = new JTextField(10);
		
		paymenttext.setEditable(false);
		
		doIt= new JButton("compute");
		
		
		gbc.weighty= 1;
		gbc.gridwidth= GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbag.setConstraints(heading, gbc);
		
		gbc.anchor = GridBagConstraints.EAST;
		
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbag.setConstraints(amountLab,gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbag.setConstraints(amounttext, gbc);
		
		gbc.gridwidth =GridBagConstraints.RELATIVE;
		gbag.setConstraints(periodLab, gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbag.setConstraints(periodtext, gbc);
		
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbag.setConstraints(rateLab,gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbag.setConstraints(ratetext, gbc);
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbag.setConstraints(paymentLab,gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbag.setConstraints(paymenttext, gbc);
		
		
		gbc.anchor = GridBagConstraints.CENTER;
		
		add(heading);
		add(amountLab);
		add(amounttext);
		add(periodLab);
		add(periodtext);
		add(rateLab);
		add(ratetext);
		add(paymentLab);
		add(paymenttext);
		add(doIt);
		
		
		amounttext.addActionListener(this);
		periodtext.addActionListener(this);
		ratetext.addActionListener(this);
		doIt.addActionListener(this);
		
		nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
	}
	
	
		
		
		public void actionPerformed( ActionEvent ae) {
			double result=0.0;
			String amountStr = amounttext.getText();
			String periodStr = periodtext.getText();
			String rateStr = ratetext.getText();
			
			try {
				if(amountStr.length()!= 0 && periodStr.length()!= 0 && rateStr.length()!= 0 && rateStr.length()!= 0){
						principal= Double.parseDouble(amountStr);
						numyears = Double.parseDouble(periodStr);
						intrate =Double.parseDouble(rateStr)/100;
					
						result = compute();
						paymenttext.setText(nf.format(result));
					
					}
				showStatus("");
				}catch(NumberFormatException ex) {
				
					System.out.println("Invalid data " +ex);
					paymenttext.setText("");
				
				}
			}	
		double compute() {
			double numer; 
			double denom;
			double b,e;
			numer = intrate * principal / 12;
			
			e= -(12 *numyears);
			b= (intrate/12) +1;
			denom = 1.0 - Math.pow(b, e);
			
			return numer / denom;
			}
		
		
	}
