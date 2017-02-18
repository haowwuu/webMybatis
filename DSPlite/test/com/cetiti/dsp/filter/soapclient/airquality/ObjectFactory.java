
package com.cetiti.dsp.filter.soapclient.airquality;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cetiti.dsp.filter.soapclient.airquality package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StationAirQualityResponse_QNAME = new QName("http://AirQuality.dsp.cetiti.com", "stationAirQualityResponse");
    private final static QName _StationAirQuality_QNAME = new QName("http://AirQuality.dsp.cetiti.com", "stationAirQuality");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cetiti.dsp.filter.soapclient.airquality
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StationAirQuality }
     * 
     */
    public StationAirQuality createStationAirQuality() {
        return new StationAirQuality();
    }

    /**
     * Create an instance of {@link StationAirQualityResponse }
     * 
     */
    public StationAirQualityResponse createStationAirQualityResponse() {
        return new StationAirQualityResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StationAirQualityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AirQuality.dsp.cetiti.com", name = "stationAirQualityResponse")
    public JAXBElement<StationAirQualityResponse> createStationAirQualityResponse(StationAirQualityResponse value) {
        return new JAXBElement<StationAirQualityResponse>(_StationAirQualityResponse_QNAME, StationAirQualityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StationAirQuality }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://AirQuality.dsp.cetiti.com", name = "stationAirQuality")
    public JAXBElement<StationAirQuality> createStationAirQuality(StationAirQuality value) {
        return new JAXBElement<StationAirQuality>(_StationAirQuality_QNAME, StationAirQuality.class, null, value);
    }

}
