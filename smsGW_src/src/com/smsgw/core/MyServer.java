package com.smsgw.core;

import android.app.PendingIntent;

public class MyServer extends Thread{

	public PendingIntent sentPI;
	public PendingIntent deliveredPI;
	
	public MyServer(PendingIntent sentPI, PendingIntent deliveredPI){
		this.sentPI=sentPI;
		this.deliveredPI=deliveredPI;
	}
	
	@Override
	public void run() {
		final MyServ srv = new MyServ();
 		// setting aliases, for an optional file servlet
                Acme.Serve.Serve.PathTreeDictionary aliases = new Acme.Serve.Serve.PathTreeDictionary();
                aliases.put("/*", new java.io.File("/tmp/"));
		//  note cast name will depend on the class name, since it is anonymous class
                srv.setMappingTable(aliases);
		// setting properties for the server, and exchangeable Acceptors
		java.util.Properties properties = new java.util.Properties();
		properties.put("port", 8081);
		properties.setProperty(Acme.Serve.Serve.ARG_NOHUP, "nohup");
		srv.arguments = properties;
		//srv.addDefaultServlets(null); // optional file servlet
		srv.addServlet("/smsgw", new MyServlet(this.sentPI, this.deliveredPI)); // optional
		// the pattern above is exact match, use /myservlet/* for mapping any path startting with /myservlet (Since 1.93)
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				srv.notifyStop();
				srv.destroyAllServlets();
			}
		}));
		srv.serve();
		System.out.println("SERVER ON");
	}

}


class MyServ extends Acme.Serve.Serve {
	private static final long serialVersionUID = 1L;
	// Overriding method for public access
	
    public void setMappingTable(PathTreeDictionary mappingtable) { 
          super.setMappingTable(mappingtable);
    }
    // add the method below when .war deployment is needed
    public void addWarDeployer(String deployerFactory, String throttles) {
          super.addWarDeployer(deployerFactory, throttles);
    }
};