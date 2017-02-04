/**
 * 
 */
package br.com.compliancesoftware.model.auxModels;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import br.com.compliancesoftware.model.auxModels.FMT.DateFormat;
import br.com.compliancesoftware.model.auxModels.FMT.NumberFormat;

/**
 * @author Douglas Fernandes <douglasf.filho@gmail.com>
 *
 */
public class FMTTest {

	/**
	 * Test method for {@link br.com.compliancesoftware.model.auxModels.FMT#getCalendarFromString(java.lang.String, br.com.compliancesoftware.model.auxModels.FMT.DateFormat)}.
	 */
	@Test
	public void testGetCalendarFromString() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 01);
		calendar.set(Calendar.MONTH, 01);
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		Calendar teste = FMT.getCalendarFromString("01/02/2017", DateFormat.DMY);
		int compare = calendar.compareTo(teste);
		assertEquals(0,compare);
	}

	/**
	 * Test method for {@link br.com.compliancesoftware.model.auxModels.FMT#getStringFromCalendar(java.util.Calendar, br.com.compliancesoftware.model.auxModels.FMT.DateFormat)}.
	 */
	@Test
	public void testGetStringFromCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 01);
		calendar.set(Calendar.MONTH, 01);
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		String teste = FMT.getStringFromCalendar(calendar, DateFormat.DMY);
		assertEquals("01/02/2017",teste);
	}

	/**
	 * Test method for {@link br.com.compliancesoftware.model.auxModels.FMT#getHojeAsString(br.com.compliancesoftware.model.auxModels.FMT.DateFormat)}.
	 */
	@Test
	public void testGetHojeAsString() {
		String teste = FMT.getHojeAsString(DateFormat.DMY);//Testado no dia 01/02/2017
		assertEquals("01/02/2017",teste);
	}

	/**
	 * Test method for {@link br.com.compliancesoftware.model.auxModels.FMT#getHoje()}.
	 */
	@Test
	public void testGetHoje() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		Calendar hoje = FMT.getHoje();
		
		int teste = hoje.compareTo(calendar);
		
		assertEquals(0, teste);
	}

	/**
	 * Test method for {@link br.com.compliancesoftware.model.auxModels.FMT#removeFormat(java.lang.String)}.
	 */
	@Test
	public void testRemoveFormat() {
		String fmtNumber = "(81)9 9672-9491";
		String unFmtNumber = "81996729491";
		String teste = FMT.removeFormat(fmtNumber);
		
		assertEquals(unFmtNumber,teste);
	}

	/**
	 * Test method for {@link br.com.compliancesoftware.model.auxModels.FMT#formatNumber(java.lang.String, br.com.compliancesoftware.model.auxModels.FMT.NumberFormat)}.
	 */
	@Test
	public void testFormatNumber() {
		try {
			String fmtNumber = "(81)9 9672-9491";
			String unFmtNumber = "81996729491";
			String teste = FMT.formatNumber(unFmtNumber, NumberFormat.PXXPXSXXXXBXXXX);
			
			assertEquals(fmtNumber, teste);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
