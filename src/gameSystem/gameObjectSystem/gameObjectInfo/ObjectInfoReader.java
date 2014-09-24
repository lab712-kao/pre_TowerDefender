package gameSystem.gameObjectSystem.gameObjectInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import gameSystem.gameObjectSystem.gameObjectInfo.ObjectInfo.TYPE;

public class ObjectInfoReader {
	
	ArrayList<ObjectInfo> SoldierInfo = null;
	ArrayList<ObjectInfo> TowerInfo = null;
	
	public ObjectInfoReader() {
		XmlPullParserFactory pullParserFactory;
		try {
			pullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = pullParserFactory.newPullParser();
			String file = "res/xml/unitinfo.xml";
			InputStream in_s = this.getClass().getClassLoader().getResourceAsStream(file);
			
		    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	        parser.setInput(in_s, null);

	        parseXML(parser);

		} catch (XmlPullParserException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException
	{
        int eventType = parser.getEventType();
        ObjectInfo currentObjectInfo = null;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name = null;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                	SoldierInfo = new ArrayList<ObjectInfo>();
                	TowerInfo = new ArrayList<ObjectInfo>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();//get the name of tag
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    break;
                    
            }
            eventType = parser.next();
        }
	}
	
	public ObjectInfo getSoldierInfoByName() {
		return null;
	}
	
	public ObjectInfo getTowerInfoByName() {
		return null;
	}
}
