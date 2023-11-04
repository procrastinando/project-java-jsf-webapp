package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class HelloBean  implements Serializable{

	private static final long serialVersionUID = -4823295172962937652L;
	
	private String message = "Your Java app is up and running on Azure";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String prompt;
	private String result = "`result` will appear here";

	// Getter and setter for prompt
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	// Getter and setter for result
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public void actionMethod() {
		result = "Hello, " + prompt + "!";
	}
}
