/**  
 * Project Name:JavaAlgorithmDS  
 * File Name:TwoArraysOperation.java  
 * Package Name:com.moonzhou.fun  
 * Date:2018年7月6日下午10:38:57  
 * Copyright (c) 2018, ayimin1989@163.com All Rights Reserved.  
 *  
*/  
  
package com.moonzhou.fun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**  
 * ClassName:TwoArraysOperation <br/>  
 * Function: 两个数组取交集，并集，差集. <br/>  
 * Date:     2018年7月6日 下午10:38:57 <br/>  
 * @author   moon-zhou  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
public class TwoArraysOperation {
	public static void main(String[] args) {
		String[] array1 = {"1", "2", "3", "4", "5"};
		String[] array2 = {"1", "3", "5", "7", "9"};
		consoleOut("原始数组1", array1);
		consoleOut("原始数组2", array2);
		
		// 交集：时间复杂度为n
		String[] intersectionOne = intersectionArraysSimple(array1, array2);
		consoleOut("交集-时间复杂度为n", intersectionOne);
		
		// 交集：时间复杂度为n的平方
		String[] intersectionTwo = intersectionArraysComplex(array1, array2);
		consoleOut("交集-时间复杂度为n的平方", intersectionTwo);

		// 并集
		String[] unions = unionArrays(array1, array2);
		consoleOut("并集", unions);
		
		// 差集
		String[] difference = differenceSet(array1, array2);
		consoleOut("差集", difference);
	}
	
	/**
	 * 
	 * intersection:俩数组交集. <br/> 
	 * 
	 * 时间复杂度为n
	 * @author moon-zhou
	 * @param array1
	 * @param array2  
	 * @since 2018年7月6日
	 */
	private static String[] intersectionArraysSimple(String[] array1, String[] array2) {
		List<String> resultList = new ArrayList<String>();
		
		//　如果一样长，第一个为long，第二个为short
		String[] longArray = array1.length >= array2.length ? array1 : array2;
		String[] shortArray = array1.length < array2.length ? array1 : array2;
		
		// 长数组作为基础数据存入不可重复的set里，遍历较少的数组
		Set<String> baseData = new HashSet<String>(Arrays.asList(longArray));
		for (int i = 0; i < shortArray.length; i++) {
			String shortString = shortArray[i];
			if (baseData.contains(shortString)) {
				resultList.add(shortString);
			}
		}
		
		String[] result = {};
		return resultList.toArray(result);
	}
	
	/**
	 * 
	 * intersection:俩数组交集. <br/> 
	 * 
	 * 时间复杂度为n的平方
	 * @author moon-zhou
	 * @param array1
	 * @param array2  
	 * @since 2018年7月6日
	 */
	private static String[] intersectionArraysComplex(String[] array1, String[] array2) {
		List<String> resultList = new ArrayList<String>();
		
		for (String s1 : array1) {
			for (String s2 : array2) {
				if (StringUtils.equals(s1, s2)) {
					resultList.add(s2);
					break;
				}
			}			
		}
		
		String[] result = {};
		return resultList.toArray(result);
	}
	
	/**
	 * 
	 * unionArrays:数组取交集. <br/> 
	 * 
	 * @author moon-zhou
	 * @param array1
	 * @param array2
	 * @return  
	 * @since 2018年7月6日
	 */
	private static String[] unionArrays(String[] array1, String[] array2) {
		
		Set<String> set1 = new HashSet<String>(Arrays.asList(array1));
		Set<String> set2 = new HashSet<String>(Arrays.asList(array2));
		
		set1.addAll(set2);
		
		String[] result = {};
		return set1.toArray(result);
	}

	/**
	 * 
	 * differenceSet:取差集. <br/> 
	 * 
	 * @author moon-zhou
	 * @param array1
	 * @param array2
	 * @return  
	 * @since 2018年7月6日
	 */
	private static String[] differenceSet(String[] array1, String[] array2) {
		
		// 去除array1和array2中的重复的值
		Set<String> set1 = new HashSet<String>(Arrays.asList(array1));
		Set<String> set2 = new HashSet<String>(Arrays.asList(array2));
		
		// set2里面的值，如果array1里存在，则提出，不存在则加入。
		for (String string : set2) {
			if (set1.contains(string)) {
				set1.remove(string);
			} else {
				set1.add(string);
			}
		}
		
		String[] result = {};
		return set1.toArray(result);
	}
	
	private static void consoleOut(String type,String[] result) {
		if (null != result) {
			System.out.print(String.format("%s:", type));
			for (String string : result) {
				System.out.print(String.format("%s  ", string));
			}
			System.out.println();
		} else {
			System.out.println("no result.");
		}
		
	}
}
  
