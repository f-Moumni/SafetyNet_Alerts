package com.safetynet.alerts.DTO;

import java.util.ArrayList;
import java.util.List;

public class ChildAlertDTO {
	private List<ChildDTO> children;
	private List<String> family = new ArrayList<>();

	public ChildAlertDTO() {
		super();
	}
	public ChildAlertDTO(List<ChildDTO> children, List<String> family) {
		super();
		this.children = children;
		this.family = family;
	}
	public List<ChildDTO> getChildren() {
		return children;
	}
	public void setChildren(List<ChildDTO> children) {
		this.children = children;
	}
	public List<String> getFamily() {
		return family;
	}
	public void setFamily(List<String> family) {
		this.family = family;
	}

}
