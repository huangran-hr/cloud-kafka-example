/**
 * 
 */
package com.sjlh.cloud.server.dto;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Administrator
 *
 */
@Getter
@Setter
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonDto {
	@XmlElement(name = "id")
	private Integer id = 1;
	@XmlElement
	private String name = "name";
	@XmlElement
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate startDate;
}