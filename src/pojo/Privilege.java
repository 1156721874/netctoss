package pojo;

import java.util.List;

public class Privilege {
private String id="";
private String name="";
private List<String> urls;
private String strurls;
public String getStrurls() {
	return strurls;
}
public void setStrurls(String strurls) {
	this.strurls = strurls;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public List<String> getUrls() {
	return urls;
}
public void setUrls(List<String> urls) {
	this.urls = urls;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
}
