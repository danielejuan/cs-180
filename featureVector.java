import java.io.*;
import java.util.*;

public class featureVector {
	String age; 
	String sex; 
	String cp; 
	String trestbps; 
	String chol; 
	String fbs; 
	String restecg; 
	String thalach; 
	String exang; 
	String oldpeak; 
	String slope; 
	String ca; 
	String thal; 
	String num;

	public List<String> getList(){
		ArrayList<String> values = new ArrayList<String>;
		values.add("age=" + age);
		values.add("sex=" + sex);
		values.add("cp=" + cp);
		values.add("trestbps=" + trestbps);
		values.add("chol=" + chol);
		values.add("fbs=" + fbs);
		values.add("restecg=" + restecg);
		values.add("thalach=" + thalach);
		values.add("exang=" + exang);
		values.add("oldpeak=" + oldpeak);
		values.add("slope=" + slope);
		values.add("ca=" + ca);
		values.add("thal=" + thal);
		values.add("num=" + num);
		return Arrays.asList(values.toArray(new String[values.size()]));
	}
	
}