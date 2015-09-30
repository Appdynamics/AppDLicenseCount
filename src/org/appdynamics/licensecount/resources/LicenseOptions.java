/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.appdynamics.licensecount.resources;

import org.appdynamics.licensecount.resources.LicenseS;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author gilbert.solorzano
 */
public class LicenseOptions {
    
    private static Logger logger=Logger.getLogger(LicenseOptions.class.getName());
    private static Options options=new Options();
    private String[] arguments;
    private CommandLine cmdLine;
    
    public LicenseOptions(String[] args){
        this.arguments=args;
        init();
    }
    
    public static void init(){
        Option controller = OptionBuilder.withLongOpt(LicenseS.CONTROLLER_L)
                                .withArgName( LicenseS.CONTROLLER_S )
                                .hasArg()
                                .withDescription( LicenseS.CONTROLLER_D )
                                .create( LicenseS.CONTROLLER_S );
        
        options.addOption(controller);
        Option port = OptionBuilder.withLongOpt(LicenseS.PORT_L).withArgName( LicenseS.PORT_S )
                                .hasArg()
                                .withDescription(  LicenseS.PORT_D )
                                .create( LicenseS.PORT_S );
        options.addOption(port);
        Option account = OptionBuilder.withLongOpt(LicenseS.ACCOUNT_L).withArgName( LicenseS.ACCOUNT_S )
                                .hasArg()
                                .withDescription( LicenseS.ACCOUNT_D )
                                .create( LicenseS.ACCOUNT_S );
        options.addOption(account);
        Option username = OptionBuilder.withLongOpt(LicenseS.USERNAME_L).withArgName( LicenseS.USERNAME_S )
                                .hasArg()
                                .withDescription( LicenseS.USERNAME_D )
                                .create( LicenseS.USERNAME_S );
        options.addOption(username);
        Option passwd = OptionBuilder.withLongOpt(LicenseS.PASSWD_L).withArgName( LicenseS.PASSWD_S )
                                .hasArg()
                                .withDescription( LicenseS.PASSWD_D )
                                .create( LicenseS.PASSWD_S );
        options.addOption(passwd);
        
        options.addOption(LicenseS.SSL_S, LicenseS.SSL_L, LicenseS.SSL_A, LicenseS.SSL_D);
        
        options.addOption(LicenseS.NOW_S, LicenseS.NOW_L, LicenseS.NOW_A, LicenseS.NOW_D);
        
        
        Option debug = OptionBuilder.withLongOpt(LicenseS.DEBUG_L).withArgName( LicenseS.DEBUG_S )
                                .hasArg()
                                .withDescription( LicenseS.DEBUG_D )
                                .create( LicenseS.DEBUG_S );
        options.addOption(debug);
        options.addOption(LicenseS.GRANULAR_S, LicenseS.GRANULAR_L, LicenseS.GRANULAR_A, LicenseS.GRANULAR_D);
        
        /* Filename & Interval */
        Option filename = OptionBuilder.withLongOpt(LicenseS.FILENAME_L).withArgName( LicenseS.FILENAME_S )
                                .hasArg()
                                .withDescription( LicenseS.FILENAME_D )
                                .create( LicenseS.FILENAME_S );
        
        options.addOption(filename);
        
        Option interval = OptionBuilder.withLongOpt(LicenseS.INTERVAL_L).withArgName( LicenseS.INTERVAL_S )
                                .hasArg()
                                .withDescription( LicenseS.INTERVAL_D )
                                .create( LicenseS.INTERVAL_S );
        options.addOption(interval);
        
        Option uptime = OptionBuilder.withLongOpt(LicenseS.UPTIME_L).withArgName( LicenseS.UPTIME_S )
                                .hasArg()
                                .withDescription( LicenseS.UPTIME_D )
                                .create( LicenseS.UPTIME_S );
        options.addOption(uptime);
        
        Option apps = OptionBuilder.withLongOpt(LicenseS.APPS_L).withArgName( LicenseS.APPS_S )
                                .hasArg()
                                .withDescription( LicenseS.APPS_D )
                                .create( LicenseS.APPS_S );
        options.addOption(apps);
 
        Option group = OptionBuilder.withLongOpt(LicenseS.GROUP_L).withArgName( LicenseS.GROUP_S )
                .hasArg()
                .withDescription( LicenseS.GROUP_D )
                .create( LicenseS.GROUP_S );
        options.addOption(group);       
    }
    
    public boolean parse(){
            CommandLineParser parser = new GnuParser();
        try {
            // parse the command line arguments
            cmdLine = parser.parse( options, arguments );
            boolean printHelp=false;
            
            /* Check to see if we have a config */
            
            
            /* Required */
            if(!cmdLine.hasOption(LicenseS.USERNAME_L) || !cmdLine.hasOption(LicenseS.USERNAME_S)){
                logger.log(Level.INFO, LicenseS.OPTION_ERROR_1 + " 1");
                return false;
            }else{ LicenseS.USERNAME_V=cmdLine.getOptionValue(LicenseS.USERNAME_S);}

            if(!cmdLine.hasOption(LicenseS.PASSWD_L) || !cmdLine.hasOption(LicenseS.PASSWD_S)){
                logger.log(Level.INFO, LicenseS.OPTION_ERROR_1+ " 1");
                return false;
            }else{ LicenseS.PASSWD_V=cmdLine.getOptionValue(LicenseS.PASSWD_S);}
            
            if(!cmdLine.hasOption(LicenseS.CONTROLLER_L) || !cmdLine.hasOption(LicenseS.CONTROLLER_S)){
                logger.log(Level.INFO, LicenseS.OPTION_ERROR_1+ " 1");
                return false;
            }else{ LicenseS.CONTROLLER_V=cmdLine.getOptionValue(LicenseS.CONTROLLER_S);}
            
            if(!cmdLine.hasOption(LicenseS.PORT_L) || !cmdLine.hasOption(LicenseS.PORT_S)){
                logger.log(Level.INFO, LicenseS.OPTION_ERROR_1+ " 1");
                return false;
            }else{ LicenseS.PORT_V=cmdLine.getOptionValue(LicenseS.PORT_S);}

            //Optional options
            if(cmdLine.hasOption(LicenseS.SSL_L) || cmdLine.hasOption(LicenseS.SSL_S)){
                LicenseS.SSL_V=true;
            }
            
            if(cmdLine.hasOption(LicenseS.NOW_L) || cmdLine.hasOption(LicenseS.NOW_S)){
                LicenseS.NOW_V=true;
            }
            
            
            if(cmdLine.hasOption(LicenseS.INTERVAL_L) || cmdLine.hasOption(LicenseS.INTERVAL_S)){
                LicenseS.INTERVAL_V=getValidNumber(cmdLine.getOptionValue(LicenseS.INTERVAL_S));
                
            }
            if(cmdLine.hasOption(LicenseS.DEBUG_L) || cmdLine.hasOption(LicenseS.DEBUG_S)){
                LicenseS.DEBUG_V=getValidNumber(cmdLine.getOptionValue(LicenseS.DEBUG_S));
                
            }
            if(cmdLine.hasOption(LicenseS.ACCOUNT_L) || cmdLine.hasOption(LicenseS.ACCOUNT_S)){
                LicenseS.ACCOUNT_V=cmdLine.getOptionValue(LicenseS.ACCOUNT_S);    
            }
            if(cmdLine.hasOption(LicenseS.FILENAME_L) || cmdLine.hasOption(LicenseS.FILENAME_S)){
                LicenseS.FILENAME_V=cmdLine.getOptionValue(LicenseS.FILENAME_S);    
            }else{
                LicenseS.FILENAME_V=new StringBuilder().append(LicenseS.ACCOUNT_V).append(LicenseS.FILE_ENDING).toString();
            }
            if(cmdLine.hasOption(LicenseS.GRANULAR_L) || cmdLine.hasOption(LicenseS.GRANULAR_S)){
                LicenseS.GRANULAR_V=true;
            }
            if(cmdLine.hasOption(LicenseS.UPTIME_L) || cmdLine.hasOption(LicenseS.UPTIME_S)){
                LicenseS.UPTIME_V=getDoubleValidNumber(cmdLine.getOptionValue(LicenseS.UPTIME_S));
                
            }
            if(cmdLine.hasOption(LicenseS.APPS_L) || cmdLine.hasOption(LicenseS.APPS_S)){
                LicenseS.APPS_V=getApps(cmdLine.getOptionValue(LicenseS.APPS_S));               
            }
            if(cmdLine.hasOption(LicenseS.GROUP_L) || cmdLine.hasOption(LicenseS.GROUP_S)){
                if (!new File(cmdLine.getOptionValue(LicenseS.GROUP_S)).exists()) {
                    logger.log(Level.WARNING, new StringBuilder().append("File: " + cmdLine.getOptionValue(LicenseS.GROUP_S) + " doesn't exist!").toString() );
                    return false;
                }
                LicenseS.GROUP_V=cmdLine.getOptionValue(LicenseS.GROUP_S);
                
            } 


        }
        catch( ParseException exp ) {
            // oops, something went wrong
            logger.log(Level.SEVERE, new StringBuilder().append("Parsing failed.  Reason: ").append(exp.getMessage()).append("\n\n").toString() );
            return false;
        }
        return true;
    }
    
    public void printHelp(){
        
        new HelpFormatter().printHelp(LicenseS.USAGE, options);
    }

    public static Options getOptions() {
        return options;
    }

    public static void setOptions(Options options) {
        LicenseOptions.options = options;
    }

    public String[] getArguments() {
        return arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }
    
    public int getValidNumber(String stringInt){
        try{
            Integer val = new Integer(stringInt);
            return val.intValue();
        }catch(Exception e){
            logger.log(Level.SEVERE, new StringBuilder().append("Exception occurred while parsing number from ")
                    .append(stringInt).append(" ").append(e.getMessage()).toString());
        }
        return -1;
    }
    
    public double getDoubleValidNumber(String stringDouble){
        try{
            Double val = new Double(stringDouble);
            if(val > 99) return -1.0;
            if(val >= 1) return val/100;
            return val;
        }catch(Exception e){
            logger.log(Level.SEVERE, new StringBuilder().append("Exception occurred while parsing number from ")
                    .append(stringDouble).append(" ").append(e.getMessage()).toString()); 
        }
        return -1.0;
    }
    
    public boolean validInterval(int interval){
        if(interval > 0 && interval < 36) return true;
        return false;
    }
    
    public boolean validDebug(int debugLevel){
        if(debugLevel >= 0 && debugLevel < 4) return true;
        return false;
    }
    
    public ArrayList<String> getApps(String apps){
        if(apps != null){
            String[] appsArr = apps.split(",");
            for(int i=0; i < appsArr.length; i++) LicenseS.APPS_V.add(appsArr[i].trim());
        }
        return LicenseS.APPS_V;
    }
    
}
