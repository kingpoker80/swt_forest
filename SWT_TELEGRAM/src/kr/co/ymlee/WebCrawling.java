package kr.co.ymlee;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.Bullet;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.ST;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;

//https://www.foresttrip.go.kr/rep/or/sssn/monthRsrvtStatus.do?hmpgId=ID02030086&menuId=001004

public class WebCrawling {

	protected Shell shlApiTest;
	protected Browser brw;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			WebCrawling window = new WebCrawling();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlApiTest.open();
		shlApiTest.layout();
		while (!shlApiTest.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlApiTest = new Shell();
		shlApiTest.addControlListener(new ControlAdapter() {
			//화면 크기 조정시 (브라우져 크기 조정필요)
			@Override
			public void controlResized(ControlEvent c) {
				System.out.println("controlResized");
				System.out.println("c.widget.getDisplay().getBounds().width :" +c.widget.getDisplay().getBounds().width);
			}
		});
		shlApiTest.setSize(676, 481);
		shlApiTest.setText("API TEST");
		
		Button btnSend = new Button(shlApiTest, SWT.NONE);
		btnSend.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				brw.setUrl(txtLocation.getText());
//				
//				String reqText = txtRequest.getText();
////				txtResponse.setText(reqText);
////				System.out.println("reqText : " +reqText);
//				if( reqText.trim().equals("") ){
//					alert("내용을 입력하세요.");
//					return;
//				}
//				
////				if( reqText.indexOf("http://") != 0){
////					alert("URL형태가 아닙니다.");
////					return;
////				}
////				if( reqText.indexOf("?") != -1){
////					alert("URL형태가 아닙니다.");
////					return;
////				}
//				getApiData();
			}
		});
		btnSend.setText("Send");
		btnSend.setBounds(611, 12, 44, 25);
		
		brw = new Browser(shlApiTest, SWT.NONE);
		brw.setBounds(10, 167, 634, 266);
		brw.addLocationListener(new LocationListener() {
			
//			@Override
			public void changing(LocationEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("##### changing : " + arg0.location);
			}
			
//			@Override
			public void changed(LocationEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("##### changed : " + arg0.location);
				System.out.println("##### changed Data : " + brw.getText());
			}
		});
		
		txtLocation = new Text(shlApiTest, SWT.BORDER);
		txtLocation.setText("https://reserve.gmuc.co.kr/user/login/login.do?menuFlag=C&chkData=N");
		txtLocation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if( e.keyCode == SWT.CR ){ //엔터
					brw.setUrl(txtLocation.getText());
				}
			}
		});
		txtLocation.setBounds(10, 14, 595, 21);
		
		button = new Button(shlApiTest, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("--------------------------------");
				System.out.println(brw.getText());
				System.out.println("--------------------------------");
			}
		});
		button.setText("확인");
		button.setBounds(529, 60, 76, 25);
		
		group = new Group(shlApiTest, SWT.NONE);
		group.setText("도덕산");
		group.setBounds(404, 41, 119, 109);
		
		checkboxTableViewer = CheckboxTableViewer.newCheckList(group, SWT.BORDER | SWT.FULL_SELECTION);
		checkboxTableViewer.setAllChecked(true);
		table = checkboxTableViewer.getTable();
		table.setLinesVisible(true);
		table.setBounds(7, 42, 106, 59);
		
		button_1 = new Button(group, SWT.CHECK);
		button_1.setText("도덕산");
		button_1.setBounds(7, 20, 94, 16);
		
		group_1 = new Group(shlApiTest, SWT.NONE);
		group_1.setText("예약");
		group_1.setBounds(38, 44, 360, 91);
		
		button_2 = new Button(group_1, SWT.NONE);
		button_2.setText("Start");
		button_2.setBounds(220, 49, 60, 20);
		
		button_3 = new Button(group_1, SWT.NONE);
		button_3.setText("Stop");
		button_3.setEnabled(false);
		button_3.setBounds(286, 49, 60, 20);
		
		text = new Text(group_1, SWT.BORDER);
		text.setToolTipText("찾는 딜레이");
		text.setText("7");
		text.setBounds(240, 22, 35, 21);
		
		label = new Label(group_1, SWT.NONE);
		label.setText("초");
		label.setBounds(318, 26, 17, 15);
		
		label_1 = new Label(group_1, SWT.NONE);
		label_1.setText("일자");
		label_1.setAlignment(SWT.RIGHT);
		label_1.setBounds(7, 48, 30, 21);
		
		text_1 = new Text(group_1, SWT.BORDER);
		text_1.setText("06#13#20#27");
		text_1.setBounds(44, 48, 170, 21);
		
		label_2 = new Label(group_1, SWT.NONE);
		label_2.setText("월");
		label_2.setAlignment(SWT.RIGHT);
		label_2.setBounds(7, 22, 30, 20);
		
		text_2 = new Text(group_1, SWT.BORDER);
		text_2.setText("201907");
		text_2.setEditable(false);
		text_2.setBounds(44, 21, 71, 23);
		
		button_6 = new Button(group_1, SWT.CHECK);
		button_6.setText("다음달");
		button_6.setBounds(121, 20, 57, 24);
		
		button_7 = new Button(group_1, SWT.CHECK);
		button_7.setText("week");
		button_7.setSelection(true);
		button_7.setBounds(181, 20, 53, 24);
		
		text_3 = new Text(group_1, SWT.BORDER);
		text_3.setToolTipText("찾은 후 딜레이");
		text_3.setText("600");
		text_3.setBounds(282, 22, 35, 21);

	}

	private Text txtLocation;
	private Button button;
	private Group group;
	private Table table;
	private CheckboxTableViewer checkboxTableViewer;
	private Button button_1;
	private Group group_1;
	private Button button_2;
	private Button button_3;
	private Text text;
	private Label label;
	private Label label_1;
	private Text text_1;
	private Label label_2;
	private Text text_2;
	private Button button_6;
	private Button button_7;
	private Text text_3;
	
	/**
	 * Alert 메세지
	 * @param str
	 */
	public static void alert(String str){
		JOptionPane.showMessageDialog(null, str);
	}
}
