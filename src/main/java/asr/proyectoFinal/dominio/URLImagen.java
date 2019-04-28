package asr.proyectoFinal.dominio;

public class URLImagen {
	private String _id ;
	private String _rev = null;
	private String url = null;
	
	public URLImagen() {
		this.url = "";
	}
	
	public String get_id() {
		return _id;
	}
	
	public void set_id(String _id) {
		this._id = _id;
	}
	
	public String get_rev() {
		return _rev;
	}
	
	public void set_rev(String _rev) {
		this._rev = _rev;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString()
	{
		return url;
	}
}
