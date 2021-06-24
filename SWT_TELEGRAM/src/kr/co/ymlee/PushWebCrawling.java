package kr.co.ymlee;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import kr.co.ymlee.util.ConstantSite;
import kr.co.ymlee.util.DateUtil;
import kr.co.ymlee.util.LymUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
//import org.apache.http.client.HttpClient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PushWebCrawling {

	protected Shell shlApiTest;
	private String DATE_PATTERN = "#";
	
	// 웹 크롤링을 위한 클래스
	public static WebDriver driver = null;
	public static String WEB_DRIVER_PATH = "C:/Users/admin/Downloads/chromedriver_32_83v.exe";


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
//			System.out.println("path : " + PushWebCrawling.class.getResource(".").getPath());
//			System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
			PushWebCrawling window = new PushWebCrawling();
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
	private static Text txtLog;
	private Label label_3;
	private Text txt_ChatId;
	private Label lblChatid;
	private Button btnTestPop;
	private Button btnTestParser;
	
	
	protected void createContents() {
		
		//배포시 주석 해제 필요.
//		String CLASS_ROOT_PATH = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getAbsolutePath();
//		System.out.println(CLASS_ROOT_PATH);
//		WEB_DRIVER_PATH = CLASS_ROOT_PATH + File.separator +"chromedriver.exe";
//		System.out.println(WEB_DRIVER_PATH);
		System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
		
		shlApiTest = new Shell();
		shlApiTest.setSize(504, 273);
		shlApiTest.setText("Push");
		shlApiTest.addShellListener(new ShellListener() {
			
			public void shellIconified(ShellEvent arg0) {}
			public void shellDeiconified(ShellEvent arg0) {}
			public void shellDeactivated(ShellEvent arg0) {}
			public void shellActivated(ShellEvent arg0) {}
			
			//크롬창 같이 닫히도록
			public void shellClosed(ShellEvent arg0) {
				if( arg0.widget == shlApiTest ){
					if( driver != null ){
						try{ driver.close(); }catch(Exception ie){}
						driver = null;
					}
				}
			}
		});
		
		
		group_1 = new Group(shlApiTest, SWT.NONE);
		group_1.setText("예약");
		group_1.setBounds(10, 10, 315, 186);
		
		txtSearchTime = new Text(group_1, SWT.BORDER);
		txtSearchTime.setToolTipText("찾는 딜레이");
		txtSearchTime.setText("30");
		txtSearchTime.setBounds(54, 75, 35, 21);
		
		label = new Label(group_1, SWT.NONE);
		label.setText("초");
		label.setBounds(90, 103, 17, 15);
		
		label_1 = new Label(group_1, SWT.NONE);
		label_1.setText("딜레이");
		label_1.setAlignment(SWT.RIGHT);
		label_1.setBounds(10, 101, 38, 21);
		
		label_2 = new Label(group_1, SWT.NONE);
		label_2.setText("주기");
		label_2.setAlignment(SWT.RIGHT);
		label_2.setBounds(18, 78, 30, 15);
		
		txtSuccDelayTime = new Text(group_1, SWT.BORDER);
		txtSuccDelayTime.setToolTipText("찾은 후 딜레이");
		txtSuccDelayTime.setText("300");
		txtSuccDelayTime.setBounds(54, 99, 35, 21);
		
		label_3 = new Label(group_1, SWT.NONE);
		label_3.setText("초");
		label_3.setBounds(90, 78, 17, 15);
		
		chkAddPushMan = new Button(group_1, SWT.CHECK);
		chkAddPushMan.setTextDirection(1656665448);
		chkAddPushMan.setToolTipText("1656665448");
		chkAddPushMan.setBounds(10, 128, 45, 16);
		chkAddPushMan.setText("순삼");
		
		label_4 = new Label(group_1, SWT.NONE);
		label_4.setText("월");
		label_4.setAlignment(SWT.RIGHT);
		label_4.setBounds(10, 22, 30, 20);
		
		label_5 = new Label(group_1, SWT.NONE);
		label_5.setText("일자");
		label_5.setAlignment(SWT.RIGHT);
		label_5.setBounds(10, 48, 30, 21);

		//월
		txtSearchMonth = new Text(group_1, SWT.BORDER);
		txtSearchMonth.setBounds(47, 21, 71, 23);
		txtSearchMonth.setText(DateUtil.getYear() + LymUtil.plusZero(DateUtil.getMonth()));
		
		//TEXT 일자
		txtSearchDay = new Text(group_1, SWT.BORDER);
		txtSearchDay.setBounds(47, 48, 190, 21);
		txtSearchDay.setText(DateUtil.getDayOfSatString(DateUtil.getYear() + LymUtil.plusZero(DateUtil.getMonth()), DATE_PATTERN));

		//다음달
		btnNextMonth = new Button(group_1, SWT.CHECK);
		btnNextMonth.setText("다음달");
		btnNextMonth.setBounds(124, 20, 57, 24);
		btnNextMonth.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent arg0) {
				if( btnNextMonth.getSelection() ){
					txtSearchMonth.setText(DateUtil.addMonth(DateUtil.getYear()+"", LymUtil.plusZero(DateUtil.getMonth())));
				}else{
					txtSearchMonth.setText(DateUtil.getYear()+"" + LymUtil.plusZero(DateUtil.getMonth()));
				}
				if( btnWeek.getSelection() ){
					txtSearchDay.setText(DateUtil.getDayOfSatString(txtSearchMonth.getText(), DATE_PATTERN));
				}
			}
			
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		//주말
		btnWeek = new Button(group_1, SWT.CHECK);
		btnWeek.setText("week");
		btnWeek.setSelection(true);
		btnWeek.setBounds(184, 20, 53, 24);
		
		button_1 = new Button(group_1, SWT.CHECK);
		button_1.setToolTipText("952878987");
		button_1.setText("야영장");
		button_1.setBounds(211, 78, 62, 16);
		
		button_2 = new Button(group_1, SWT.CHECK);
		button_2.setToolTipText("952878987");
		button_2.setText("숙소");
		button_2.setBounds(155, 77, 45, 16);
		
		btnStart = new Button(group_1, SWT.NONE);
		btnStart.setBounds(184, 151, 60, 25);
		btnStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				SiteSearchInfo siteSearchInfo = new SiteSearchInfo();

				//찾는 주기 셋팅
				int searchTime = LymUtil.getIntValue(txtSearchTime.getText(), 10); //기본 10초
				siteSearchInfo.setSearchTime(searchTime * 1000);

				// 찾기 성공 후 다시 찾는 주기.
				int succSleepTime = LymUtil.getIntValue(txtSuccDelayTime.getText(), 5*60) * 1000; //기본 5분
				siteSearchInfo.setSuccSleepTime(succSleepTime);
				
				SiteInfo siteInfo = new SiteInfo();
				siteInfo.setChatId(txt_ChatId.getText());
				siteInfo.setSearchYyyyMm(txtSearchMonth.getText());
				siteInfo.setSearchYyyyMmDd(txtSearchDay.getText());
				
				//안면도 ID02030086, 강씨봉 ID02030019, 고대산 ID02030001, 칼봉산 ID02030099
				if( btnGangSsi.getSelection() ){
					siteInfo.setWhatSite(ConstantSite.GANGSSI);
				}else if( btnAnmyun.getSelection() ){
					siteInfo.setWhatSite(ConstantSite.ANMYUN);
				}else if( btnGodae.getSelection() ){
					siteInfo.setWhatSite(ConstantSite.GODAE);
				}else if( btnKalBong.getSelection() ){
					siteInfo.setWhatSite(ConstantSite.KALBONG);
				}else if( btnSinSiDo.getSelection() ){
					siteInfo.setWhatSite(ConstantSite.SINSIDO);
				}
				
				if( siteInfo.getWhatSite() == null ){
					alert("어떤거 찾을껀데?");
					return;
				}
				
				if( LymUtil.isNull(txtLoginId.getText()) || LymUtil.isNull(txtLoginPwd.getText()) ){
					alert("ID, Password 입력해.");
					txtLoginId.setFocus();
					return;
				}else{
					siteInfo.setLoginId(txtLoginId.getText());
					siteInfo.setLoginPwd(txtLoginPwd.getText());
				}
				
//				siteInfo.setSearchUrl(txtLocation.getText());
				siteSearchInfo.setSiteInfo(siteInfo);
				
				workerManager = new WorkerManager(siteSearchInfo);
				workerManager.start();

				btnStart.setEnabled(false);
				btnStop.setEnabled(true);
			}
		});
		btnStart.setText("Start");
		
		btnStop = new Button(group_1, SWT.NONE);
		btnStop.setBounds(245, 151, 60, 25);
		btnStop.setText("Stop");
		btnStop.setEnabled(false);
		
		lblChatid = new Label(group_1, SWT.NONE);
		lblChatid.setBounds(4, 156, 49, 20);
		lblChatid.setText("ChatId(,)");
		lblChatid.setAlignment(SWT.RIGHT);
		
		txt_ChatId = new Text(group_1, SWT.BORDER);
		txt_ChatId.setBounds(59, 153, 122, 21);
		txt_ChatId.setText("887280669");
		
		txtLoginId = new Text(group_1, SWT.BORDER);
		txtLoginId.setBounds(183, 103, 122, 21);
		
		txtLoginPwd = new Text(group_1, SWT.BORDER);
		txtLoginPwd.setBounds(183, 128, 122, 21);
		
		lblId = new Label(group_1, SWT.NONE);
		lblId.setText("ID");
		lblId.setAlignment(SWT.RIGHT);
		lblId.setBounds(124, 103, 49, 20);
		
		lblPwd = new Label(group_1, SWT.NONE);
		lblPwd.setText("Password");
		lblPwd.setAlignment(SWT.RIGHT);
		lblPwd.setBounds(124, 128, 49, 20);
		btnStop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if( workerManager != null ){
					workerManager.userStop();
					workerManager = null;
				}
				btnStart.setEnabled(true);
				btnStop.setEnabled(false);
			}
		});
		btnWeek.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent arg0) {
				if( btnWeek.getSelection() && !LymUtil.isNull(txtSearchMonth.getText()) ){
					txtSearchDay.setText(DateUtil.getDayOfSatString(txtSearchMonth.getText(), DATE_PATTERN));
				}
			}
			
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		chkAddPushMan.addSelectionListener(new SelectionListener() {
			
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			
			public void widgetSelected(SelectionEvent e) {
				if( chkAddPushMan.getSelection() ){
					txt_ChatId.setText(txt_ChatId.getText()+","+chkAddPushMan.getToolTipText());
				}else{
					String tData = txt_ChatId.getText();
					String tResult = "";
					if( tData!=null && tData.length() > 0){
						for(String tStr : tData.split(",")){
							if( !"".equals(tStr) && !tStr.equals(chkAddPushMan.getToolTipText()) ){
								if( !tResult.equals("") ) tResult += ",";
								tResult += tStr;
							}
						}
					}
					txt_ChatId.setText(tResult);
				}
			}
		});
		
		txtLog = new Text(shlApiTest, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		txtLog.setEditable(false);
		txtLog.setBounds(334, 148, 147, 79);
		

		// ------------------------------------------------ 테스트   --------------------------------------------------------------
		
		btnTest = new Button(shlApiTest, SWT.NONE);
		btnTest.setText("푸시TEST");
		btnTest.setBounds(83, 202, 60, 25);
		btnTest.addSelectionListener(addTestListener);
		
		btnTestPop = new Button(shlApiTest, SWT.NONE);
		btnTestPop.setText("POPUP");
		btnTestPop.setBounds(147, 202, 60, 25);
		btnTestPop.addSelectionListener(addTestListener);
		
		btnTestParser = new Button(shlApiTest, SWT.NONE);
		btnTestParser.setEnabled(false);
		btnTestParser.setText("PARSER");
		btnTestParser.setBounds(20, 202, 60, 25);
		btnTestParser.addSelectionListener(addTestListener);
		
		//경로 지정
		txtLocation = new Text(shlApiTest, SWT.BORDER);
		txtLocation.setBounds(213, 204, 109, 21);
		txtLocation.setText("table#dayListTable thead tr");
		txtLocation.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if( e.keyCode == SWT.CR ){ //엔터
//					brw.setUrl(txtLocation.getText());
				}
			}
		});

		// ------------------------------------------------ 테스트   --------------------------------------------------------------
		
		
		btnGangSsi = new Button(shlApiTest, SWT.CHECK);
		btnGangSsi.setText("강씨봉");
		btnGangSsi.setBounds(341, 10, 94, 16);
		
		btnAnmyun = new Button(shlApiTest, SWT.CHECK);
		btnAnmyun.setText("안면도");
		btnAnmyun.setBounds(341, 32, 94, 16);
		
		btnGodae = new Button(shlApiTest, SWT.CHECK);
		btnGodae.setText("고대산");
		btnGodae.setBounds(341, 55, 94, 16);
		
		btnKalBong = new Button(shlApiTest, SWT.CHECK);
		btnKalBong.setText("칼봉산");
		btnKalBong.setBounds(341, 78, 94, 16);
		
		btnSinSiDo = new Button(shlApiTest, SWT.CHECK);
		btnSinSiDo.setText("신시도");
		btnSinSiDo.setBounds(341, 100, 94, 16);
		
		
		
	}
	
	private SelectionAdapter addTestListener = new SelectionAdapter() {

		public void widgetDefaultSelected(SelectionEvent e) {
			super.widgetDefaultSelected(e);
		}

		public void widgetSelected(SelectionEvent evt) {

			if( evt.widget.equals(btnTest) ){

				System.out.println("--------------------------------");
				//푸시 테스트
				//푸시전송("887280669", "테스트 전송");
				
				String tOrg = txt_ChatId.getText();
				
				if( tOrg == null || tOrg.trim().length() == 0 ) tOrg = "887280669";
				
//				String[] chatId = new String[]{"887280669", "952878987"};
				
				for(String str : tOrg.split(",")){
					if( !LymUtil.isNull(str) ){
						푸시전송(str, String.format("테스트%s", "ID02030086"));
					}
				}
				
//				if( driver == null ){
//					return;
//				}
//				String finalResult= "";
//				testDoc = 자리찾기("202005");
//				if( testDoc != null ){
//					finalResult = WorkJob.빈자리일자체크(testDoc, new String[]{"1", "2", "3", "4"});
//					System.out.println("finalResult : " + finalResult);
//				}
				
			}else if( evt.widget.equals(btnTestPop) ){

				String siteUrl2 = "https://www.foresttrip.go.kr/rep/or/sssn/monthRsrvtStatus.do?hmpgId=ID02030001&menuId=001004";
				/**
				 * 기다리게 하는 로직
				 * nosuchelementexception
				WebDriverWait wait = new WebDriverWait(webDriver, timeoutInSeconds);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id<locator>));
				 */
				
				if( driver == null ){
					driver = new ChromeDriver();
				}
				driver.get(siteUrl2);
				
				//로그인 실패라면
				if( driver.findElements(By.id("mmberId")).size() > 0 ){
					WebElement userId = driver.findElement(By.id("mmberId"));
//					WebElement userPwd = driver.findElement(By.id("gnrlMmberPssrd"));

					if( userId != null ){
//						userId.sendKeys(""); //ID
//						userPwd.sendKeys(""); //PWD
						driver.findElement(By.xpath("//input[@value='로그인']")).click();
						
						if( driver.getCurrentUrl().indexOf("sssn/monthRsrvtStatus") == -1){
							System.out.println("--------- 페이지 다른곳으로 이동 --------");
							driver.get(siteUrl2);
						}
					}
				}

				System.out.println("--------------------------------");
				System.out.println(driver.getPageSource());
				System.out.println("--------------------------------");
				System.out.println(driver.getCurrentUrl());
				System.out.println("--------------------------------");
				System.out.println(driver.getTitle());
				System.out.println("--------------------------------");
			
			}else if( evt.widget.equals(btnTestParser) ){
				
				try{
//					String exp = txtLocation.getText();
//					Elements el = testDoc.select(exp);
//					System.out.println("#1:"+ el.toString());
					
					Document document = null;
					HttpClient httpClient = getHttpClient2();
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					UrlEncodedFormEntity strent = new UrlEncodedFormEntity(params, "UTF-8");
					
					HttpPost httppost = new HttpPost("http://10.52.37.165:8000/temp2.html");
					httppost.setEntity(strent);
					
					HttpResponse response = httpClient.execute(httppost);
					HttpEntity resEntity = response.getEntity();
					InputStream stm = null;
					BufferedReader bufReader = null;

					StringBuffer responseData = new StringBuffer();
					try {
						stm = resEntity.getContent();
						bufReader = new BufferedReader(new InputStreamReader(stm, "UTF-8"));

						String bufferRead = "";
						
						while ((bufferRead = bufReader.readLine()) != null) {
							responseData.append(bufferRead);
						}

						bufReader.close();
						stm.close();

					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						if (stm != null) stm.close();
						if (bufReader != null) bufReader.close();
					}
					
					try{
						
						document = Jsoup.parse(responseData.toString());
						System.out.println("#2:"+responseData.toString());
						System.out.println("결과 데이터 :" +  WorkJob.빈자리일자체크(document, txtSearchDay.getText().split("[#]")));
//						푸시전송(chatId, fcmMessage);
//						Elements tel = document.select(exp);
						
						
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}else{
				System.out.println("Button Event Nothing :" + evt.widget.toString());
			}
		}

	};
	
	

	
	
	private static Text txtLocation;
	private Button btnTest;
	private Group group_1;
	private Button btnStart;
	private Button btnStop;
	private Text txtSearchTime;
	private Label label;
	private Label label_1;
	private Label label_2;
	private Text txtSuccDelayTime;
	private Button chkAddPushMan;
	static SoundPlay soundPlay = null;
	
	/**
	 * Alert 메세지
	 * @param str
	 */
	public static void alert(String str){
		JOptionPane.showMessageDialog(null, str);
	}
	

	static public void doTrustToCertificates() throws Exception {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
			public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				return;
			}
			public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				return;
			}
		} };
		SSLContext sc = SSLContext.getInstance("TLSv1.2");
		sc.init(null, trustAllCerts, new SecureRandom());
		
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HostnameVerifier hv = new HostnameVerifier() {
			public boolean verify(String urlHostName, SSLSession session) {
				if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
					System.out.println("Warning: URL host '" + urlHostName
							+ "' is different to SSLSession host '"
							+ session.getPeerHost() + "'.");
				}
				return true;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(hv);
	}
	
	CookieStore cookieStore = new BasicCookieStore();
	public static CloseableHttpClient getHttpClient2() throws Exception{
		SSLContext sslContext = SSLContexts.custom()
				.useProtocol("TLSv1.2")
	            .loadTrustMaterial(null, new TrustStrategy() {
	                public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException{ return true; }
	            })
	            .build();
	    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
//		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1.2" }, null,
//                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		CookieStore cookieStore = new BasicCookieStore();
	    HttpClientContext context = HttpClientContext.create();
	    context.setCookieStore(cookieStore);

		return HttpClients.custom()
				.setDefaultCookieStore(cookieStore)
	    		.setSSLSocketFactory(sslsf)
	    		.build();
	}
	
	public static void 푸시전송(String chatId, String fcmMessage){
		
		try{

//			System.setProperty("https.protocols", "TLSv1.2");
//			java.lang.System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
			//System.setProperty("jsse.enableSNIExtension", "false");

//			doTrustToCertificates();
//			HttpClient httpClient = HttpClients.createDefault();
			
//			SSLContext ctx = SSLContexts.custom().useProtocol("TLSv1.2").build();
//			HttpClient httpClient = HttpClientBuilder.create().setSslcontext(ctx).build();
			HttpClient httpClient = getHttpClient2();
//			httpClient.getParams().setParameter("http.protocol", "TLSv1.2");

			
			
//			final String apiKey = "1071527745:AAHwto3nIZDqyV_zOMBmzldAe-S57g09zSk";
			final String apiKey = "1627097682:AAHdBcBtth7Zgtje7MsQB2dYGk-n4ASUszM";
//			String chatId = "887280669";
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("chat_id", chatId));
			params.add(new BasicNameValuePair("text", fcmMessage));
			UrlEncodedFormEntity strent = new UrlEncodedFormEntity(params, "UTF-8");
			
			
			HttpPost httppost = new HttpPost("https://api.telegram.org/bot"+apiKey+"/sendmessage");
//			httppost.setHeader("Content-Type", "application/json");
//			httppost.setHeader("Authorization", "key=" + Constants.FCM_SERVER_KEY);
			httppost.setEntity(strent);
			
			HttpResponse response = httpClient.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			InputStream stm = null;
			BufferedReader bufReader = null;

			try {
				stm = resEntity.getContent();
				bufReader = new BufferedReader(new InputStreamReader(stm, "euc-kr"));

				String bufferRead = "";
				StringBuffer responseData = new StringBuffer();
				while ((bufferRead = bufReader.readLine()) != null) {
					responseData.append(bufferRead);
				}
				System.out.println("#FCM 푸시 결과 :" + responseData.toString() + "");

				bufReader.close();
				stm.close();

			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if (stm != null) stm.close();
				if (bufReader != null) bufReader.close();
			}

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public static void 찾기성공(SiteInfo siteInfo){
		//Exception in thread "Thread-0" org.eclipse.swt.SWTException: Invalid thread access
		Display.getDefault().asyncExec(new Runnable(){
			public void run(){
				txtLog.setText(DateUtil.getCurrentDate("HH:mm") + "] 찾기 성공!!");
			}
		});

		try{
			if( soundPlay == null ) soundPlay = new SoundPlay();
			if( !soundPlay.isPlay() ){
				soundPlay.start();
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			soundPlay = null;
		}

		if( siteInfo != null ){

			String viewMsg =  siteInfo.getWhatSite().getSiteName() + "["+DateUtil.getTimeString()+"] : ["+siteInfo.getFindInfo()+"]" 
					+ "https://www.foresttrip.go.kr/rep/or/sssn/monthRsrvtStatus.do?hmpgId=%s&menuId=001004";
			
			String tOrg = siteInfo.getChatId();
			
			if( tOrg == null || tOrg.trim().length() == 0 ) tOrg = "887280669";
			
//			String[] chatId = new String[]{"887280669", "952878987"};
			
			for(String str : tOrg.split(",")){
				if( !LymUtil.isNull(str) ){
					푸시전송(str, String.format(viewMsg, siteInfo.getSiteCode()));
				}
			}

			if( !isDialogOpen ){
				isDialogOpen = true;

				final String viewMsg2 = viewMsg;
				Display.getDefault().asyncExec(new Runnable(){
					public void run(){
						int btnResult = JOptionPane.showConfirmDialog(null
											, viewMsg2
											, "야호~", JOptionPane.OK_CANCEL_OPTION
											, JOptionPane.INFORMATION_MESSAGE);
						if( btnResult == JOptionPane.OK_OPTION ){
//							for(SiteInfo siteInfo : resultList2){
//
//								switch (siteInfo.getWhatSite()) {
//
//									case DODUKSAN: //도덕산
//										org.eclipse.swt.program.Program.launch(Constants.CONNECT_URL_DODUKSAN_LOGIN);
//										break;
//									case TOHAM: //토함산
//										org.eclipse.swt.program.Program.launch(Constants.CONNECT_URL_TOHAM_LIST);
//										break;
//									default:
//										org.eclipse.swt.program.Program.launch(Constants.SITE_OPEN_URL + "?companycode="+siteInfo.getSiteCode()+"&shopcode="+siteInfo.getShopCode());
//										break;
//								}
//							}
						}
						isDialogOpen = false;
					}
				});

			}

//			soundPlay.userStop();
			soundPlay = null;
		}
	}
	
	
	
	WorkerManager workerManager;
	public static boolean isDialogOpen = false;
	private Label label_4;
	private Label label_5;
	private Text txtSearchDay;
	private Text txtSearchMonth;
	private Button btnNextMonth;
	private Button btnWeek;
	private Button button_1;
	private Button button_2;
	private Button btnGangSsi;
	private Button btnAnmyun;
	private Button btnGodae;
	private Button btnKalBong;
	private Button btnSinSiDo;
	public Text txtLoginId;
	public Text txtLoginPwd;
	private Label lblId;
	private Label lblPwd;
	static class WorkerManager extends Thread{

		private SiteSearchInfo siteSearchInfo;
		private boolean isSucces = false;
		private boolean isUserStop = false;
		private int sTime = 10 * 1000;
		private int succSleepTime = 10 * 60 * 1000;

		public WorkerManager(SiteSearchInfo siteSearchInfo){
			
			//초기화
			this.siteSearchInfo = siteSearchInfo;
			if( driver == null ){
				driver = new ChromeDriver();
				//driver.get(siteUrl);
				//로그인체크();
			}
			this.siteSearchInfo.setDriver(driver);

			
			//찾는 주기
			if( siteSearchInfo.getSearchTime() > 0 ){
				this.sTime = siteSearchInfo.getSearchTime();
			}
			// 찾기 성공 후 다시 찾는데 까지 대기 시간.
			if( siteSearchInfo.getSuccSleepTime() > 0 ){
				succSleepTime = siteSearchInfo.getSuccSleepTime();
			}
			isDialogOpen = false;
		}

		@Override
		public void run() {
			while(!isUserStop){ //&& !isSucces

				try {
					if( isSucces ) {
						isSucces = false;
						sleep(succSleepTime);
					}else{
						exec();
						if( sTime > 0 ) sleep(sTime);
					}

				} catch (InterruptedException e) {
					isUserStop = true;
					System.out.println("WorkerManager InterruptedException ");
//					e.printStackTrace();
				} catch (Exception e) {
					isUserStop = true;
					System.out.println("WorkerManager Exception ");
//					e.printStackTrace();
				}
			}
			if( driver != null ){
				try{ driver.close(); }catch(Exception ie){}
				driver = null;
			}
		}

		public synchronized void exec(){
			
			if( siteSearchInfo.getErrorCount() >= 10){
				푸시전송("887280669", "자리찾기 : "+siteSearchInfo.getErrorCount() + "에러발생으로 재시작");
				siteSearchInfo.setErrorCount(0);
//				userStop();
				if( driver != null ){
					try{ driver.close(); }catch(Exception ie){}				
					System.out.println("#ERROR : driver is : " + (driver == null ? "null" : "not null"));
				}else{
					System.out.println("#ERROR : driver is null");
				}
				driver = null;
				this.siteSearchInfo.setDriver(null);
			}
			
			WorkJob work = new WorkJob(siteSearchInfo);
			SiteInfo rSiteInfo = work.execute();
			if( rSiteInfo != null ){
				isSucces = true;
				찾기성공(rSiteInfo); //검색 성공시 결과 창 호출
			}

		}

		public void userStop(){
			isUserStop = true;
			isDialogOpen = false;
		}

	}
	
	

	static class SoundPlay extends Thread{
		Clip clip = null;
		private boolean isPlay = false;
		String soundFilePath = System.getProperty("user.dir")+"/media/call.wav";

		@Override
		public void run() {
			try {
				isPlay = true;
				clip = javax.sound.sampled.AudioSystem.getClip();
				clip.open(javax.sound.sampled.AudioSystem.getAudioInputStream(new File(soundFilePath)));
				clip.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
			isPlay = false;
		}
		public boolean isPlay(){
			return isPlay;
		}
	}
}



class WorkJob{

	public static final int DEFAULT_DELEY_TIME = 60 * 1000;
	//안면도
//	public static String siteUrl = "https://www.foresttrip.go.kr/rep/or/sssn/monthRsrvtStatus.do?hmpgId=ID02030086&menuId=001004";
	//고대산
	//public static String siteUrl = "https://www.foresttrip.go.kr/rep/or/sssn/monthRsrvtStatus.do?hmpgId=ID02030001&menuId=001004";
	
	public static String siteUrl = "https://www.foresttrip.go.kr/rep/or/sssn/monthRsrvtStatus.do?hmpgId=%s&menuId=001004";
	
	public static SiteSearchInfo siteSearchInfo;
	public static WebDriver driver;
	
	public WorkJob(SiteSearchInfo siteSearchInfo){
		WorkJob.siteSearchInfo = siteSearchInfo;
//		System.out.println("#### : driver is : " + (siteSearchInfo.getDriver() == null ? "null" : "not null"));
		WorkJob.driver = siteSearchInfo.getDriver();
	}
	/**
	 * START
	 * @return
	 */
	@SuppressWarnings("static-access")
	public SiteInfo execute(){
		
		
		Document doc = 자리찾기(siteSearchInfo.getSiteInfo());
		if( doc != null ){
//			finalResult = 빈자리일자체크(source, siteInfo.getSearchYyyyMmDd().split("[#]"), siteInfo.getSearchGroupCode());
			String finalResult = 빈자리일자체크(doc, siteSearchInfo.getSiteInfo().getSearchYyyyMmDd().split("[#]"));
			if( !"".equals(finalResult) ){
				this.siteSearchInfo.getSiteInfo().setFindInfo(finalResult);
				return this.siteSearchInfo.getSiteInfo();
			}
		}
		if( this.driver == null ){
			this.siteSearchInfo.setDriver(null);
		}
		return null;
	}


	public static Document 자리찾기(SiteInfo siteInfo){
		Document returnValue = null;

		
		try{
			
			if( driver == null ){
				driver = new ChromeDriver();
				WorkJob.siteSearchInfo.setDriver(driver);
				driver.get(String.format(siteUrl, siteInfo.getSiteCode()) );
//				System.out.println("driver is null");
			}else{
				System.out.println("자리찾기 driver is not null");
			}
			
			if( driver.getCurrentUrl().indexOf("sssn/monthRsrvtStatus") == -1){
				driver.get(String.format(siteUrl, siteInfo.getSiteCode()) );
			}
			WorkJob.로그인체크(siteInfo);
			
//			driver.wait(5000);
//			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			//월
			//txtSuccDelayTime.getText()
			Select selectMonth = new Select(driver.findElement(By.id("monthSelectBox")));
			if( !siteInfo.getSearchYyyyMm().equals(selectMonth.getFirstSelectedOption().getAttribute("value"))){
				selectMonth.selectByValue(siteInfo.getSearchYyyyMm());
			}
			//숙박시설
			Select selectRoomType = new Select(driver.findElement(By.id("srchForest")));
			if( !"숙소".equals(selectRoomType.getFirstSelectedOption().getText()) ){
				selectRoomType.selectByVisibleText("숙소");
			}
//			System.out.println("selectRoomType : " + selectRoomType.getFirstSelectedOption().getText());
			
			WebElement searchBtn = driver.findElement(By.id("searchBtn"));
			searchBtn.click();
//			searchBtn.sendKeys(Keys.ENTER);
			
			WebDriverWait wait = new WebDriverWait(driver, 12);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dayListTbody")));
			
			//조회를 눌렀는데 로그인 실패라면.
			if( WorkJob.로그인체크(siteInfo) ){
				return null;
			}
			
//			System.out.println("#############     1 START     #################");
//			System.out.println(driver.getPageSource());
//			System.out.println("#############     1 END       #################");
			returnValue = Jsoup.parse(driver.getPageSource());
			System.out.println("조회 성공:"+DateUtil.getTimeString());
			siteSearchInfo.setErrorCount(0);
			
		}catch(ElementClickInterceptedException e){
			
			siteSearchInfo.addErrorCount();
			System.out.println("ElementClickInterceptedException:" + siteSearchInfo.getErrorCount());
			e.printStackTrace();
			
			try{
				if( driver != null ){
					Alert alert = driver.switchTo().alert();
					System.out.println(alert.getText()+" Alert is Displayed");
//					alert.accept();
					alert.dismiss();
//					alert.sendKeys(Keys.ESCAPE);
					//space로 alert 처리.
					WebElement body = driver.findElement(By.tagName("body"));
					body.sendKeys(Keys.SPACE);
				}else{
					System.out.println("ElementClickInterceptedException : driver is null");
				}

			}catch(Exception e2){ }
			
			try{
				if( siteSearchInfo.getErrorCount() == 6 ){
					driver.get(String.format(siteUrl, siteInfo.getSiteCode()) );
				}				
			}catch(Exception ee){ }

		}catch(Exception e){
			
			e.printStackTrace();
			siteSearchInfo.addErrorCount();
//			if( driver != null ){
//				try{ driver.close(); }catch(Exception ie){}				
//				driver = null;
//				System.out.println("#1 : driver is not null");
//				System.out.println("#1 : driver is : " + (driver == null ? "null" : "not null"));
//			}else{
//				System.out.println("#1 : driver is null");
//			}
			
			try{
				if( driver != null ){
					Alert alert = driver.switchTo().alert();
					System.out.println(alert.getText()+" Alert is Displayed");
//					alert.accept();
					alert.dismiss();
	//				alert.sendKeys(Keys.ESCAPE);
					//space로 alert 처리.
					WebElement body = driver.findElement(By.tagName("body"));
					body.sendKeys(Keys.SPACE);
					
				}else{
					System.out.println("자리찾기 Exception : driver is null");
				}
			}catch(Exception e2){ }
		}

		return returnValue;
	}
	public static boolean 로그인체크(SiteInfo siteInfo){
		//로그인 실패라면
		if( driver.findElements(By.id("mmberId")).size() > 0 ){
			WebElement userId = driver.findElement(By.id("mmberId"));
			WebElement userPwd = driver.findElement(By.id("gnrlMmberPssrd"));

			if( userId != null ){
				userId.sendKeys(siteInfo.getLoginId());
				userPwd.sendKeys(siteInfo.getLoginPwd());
				driver.findElement(By.xpath("//input[@value='로그인']")).click();
				
				if( driver.getCurrentUrl().indexOf("sssn/monthRsrvtStatus") == -1){
					System.out.println("--------- 페이지 다른곳으로 이동 --------");
					JavascriptExecutor jsExec = (JavascriptExecutor)driver;
					jsExec.executeScript("movePageUsingNetFunnel('/rep/or/sssn/monthRsrvtStatus.do?hmpgId=ID02030001&menuId=001004', 'action3');");
//					driver.get(siteUrl);
					//javascript:movePageUsingNetFunnel('/rep/or/sssn/monthRsrvtStatus.do?hmpgId=ID02030001&menuId=001004', 'action3');
					try {
						driver.wait(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			return true;
		}else{
			return false;
		}
	}
	public static String 빈자리일자체크(Document doc, String[] arrStrSearchDay) {
//	private String 빈자리일자체크(Document doc, SiteInfo siteInfo) {
		
		String returnValue = "";
//		String[] arrStrSearchDay = siteInfo.getSearchYyyyMmDd().split("#");
		int[] arrSearchDay = new int[arrStrSearchDay.length];
		Arrays.fill(arrSearchDay, -1);
		
		Elements thList = doc.select("table#dayListTable thead tr:eq(1) th");
		if( thList == null || thList.size() == 0) return returnValue;
			
		int thI = 1;
		for(Element th : thList){
//			if( thI == 1 ){
//				System.out.println("thList ### : " + thList.toString());
//			}
			for(int i=0; i < arrStrSearchDay.length; i++){
				if( th.text().equals(arrStrSearchDay[i]) || th.text().equals(""+Integer.parseInt(arrStrSearchDay[i])) ) {
					arrSearchDay[i] = thI;
					break;
				}
			}
			thI++;
		}
		for(int i : arrSearchDay){
			System.out.println("arrSearchDay : " + i);
			Elements tdList = doc.select("#dayListTbody td:nth-child("+i+")");
			//Elements tdList = doc.select("#dayListTable tbody td:nth-child("+i+")");
			for(Element elTd : tdList){
				Elements daySpan = elTd.select("a.rsrvtSelectBtn span");
				if( daySpan != null && daySpan.size() >= 1 ){
					System.out.println("title:"+daySpan.attr("title"));
					System.out.println("text:"+daySpan.text());
					if( !"예".equals(daySpan.text()) || daySpan.attr("title").indexOf("휴양관1호") > -1){
						continue;
					}
					
//					if( daySpan.attr("title").indexOf("뻐꾸기") > -1 
//							|| daySpan.attr("title").indexOf("부엉이") > -1
//							|| daySpan.attr("title").indexOf("딱따구리") > -1
//							|| daySpan.attr("title").indexOf("산까치") > -1
//							|| daySpan.attr("title").indexOf("곤줄박이") > -1
//							|| daySpan.attr("title").indexOf("황조롱이") > -1 ){
//						returnValue += ","+daySpan.attr("title");
//					}
					
					returnValue += ","+daySpan.attr("title");
				}
			}
		}
		return returnValue;
	}
	
	

	CookieStore cookieStore = new BasicCookieStore();
	public CloseableHttpClient getHttpClient() throws Exception{

		SSLContext sslContext = SSLContexts.custom()
	            .loadTrustMaterial(null, new TrustStrategy() {
	                public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException{ return true; }
	            })
	            .build();
	    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

		CookieStore cookieStore = new BasicCookieStore();
	    HttpClientContext context = HttpClientContext.create();
	    context.setCookieStore(cookieStore);

		return HttpClients.custom()
				.setDefaultCookieStore(cookieStore)
	    		.setSSLSocketFactory(sslsf)
	    		.build();
	}

}



