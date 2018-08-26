package bidwin.models;

import org.json.JSONObject;

public class Product {
	
	private long id;
	private String name;
	private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public JSONObject toJSON() {
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id",this.id);
		jsonObject.put("name",this.name);
		jsonObject.put("description",this.description);
		return jsonObject;
	}
}
