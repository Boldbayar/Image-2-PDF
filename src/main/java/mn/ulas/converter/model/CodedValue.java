package mn.ulas.converter.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CodedValue implements ValueLabelPair {

  private Object code;
  private String name;

  public CodedValue() {
  }

  public CodedValue(Object code, String name) {
    this.code = code;
    this.name = name;
  }

  @XmlElement(name = "Code")
  public Object getCode() {
    return code;
  }

  public void setCode(Object code) {
    this.code = code;
  }

  @XmlElement(name = "Name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public Object getValue() {
    return code;
  }

  @Override
  public String getLabel() {
    return name;
  }

}
