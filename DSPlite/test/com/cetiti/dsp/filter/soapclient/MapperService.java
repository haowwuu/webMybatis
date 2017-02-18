
package com.cetiti.dsp.filter.soapclient;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MapperService", targetNamespace = "http://Mapper.dsp.cetiti.com", wsdlLocation = "http://10.70.7.72:8081/com.cetiti.dsp/Mapper?wsdl")
public class MapperService
    extends Service
{

    private final static URL MAPPERSERVICE_WSDL_LOCATION;
    private final static WebServiceException MAPPERSERVICE_EXCEPTION;
    private final static QName MAPPERSERVICE_QNAME = new QName("http://Mapper.dsp.cetiti.com", "MapperService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://10.0.10.78:8081/com.cetiti.dsp/Mapper?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MAPPERSERVICE_WSDL_LOCATION = url;
        MAPPERSERVICE_EXCEPTION = e;
    }

    public MapperService() {
        super(__getWsdlLocation(), MAPPERSERVICE_QNAME);
    }

    public MapperService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MAPPERSERVICE_QNAME, features);
    }

    public MapperService(URL wsdlLocation) {
        super(wsdlLocation, MAPPERSERVICE_QNAME);
    }

    public MapperService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MAPPERSERVICE_QNAME, features);
    }

    public MapperService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MapperService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Mapper
     */
    @WebEndpoint(name = "MapperPort")
    public Mapper getMapperPort() {
        return super.getPort(new QName("http://Mapper.dsp.cetiti.com", "MapperPort"), Mapper.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Mapper
     */
    @WebEndpoint(name = "MapperPort")
    public Mapper getMapperPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://Mapper.dsp.cetiti.com", "MapperPort"), Mapper.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MAPPERSERVICE_EXCEPTION!= null) {
            throw MAPPERSERVICE_EXCEPTION;
        }
        return MAPPERSERVICE_WSDL_LOCATION;
    }

}