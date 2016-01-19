package com.walmart.retailtech.move.innovationte.timemachine.interactors.implementations;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import com.walmart.retailtech.move.innovationte.timemachine.parameters.TimeMachineSettings;

public class TimeMachine 
{	
	//-------------------- milliseconds timestamp to YYYYMMDD ---------------------------
	public String SeperatedMilliToYYMMDD( long milliseconds, String seperator )
	 throws Exception
	{	
		Instant inst = Instant.ofEpochMilli( milliseconds );
		ZonedDateTime zdutc = ZonedDateTime.ofInstant( inst, ZoneOffset.UTC );
		
		if( !seperator.equals( "-" ) )
			return zdutc.toLocalDate().toString().replaceAll( "-", seperator );
		else
			return zdutc.toLocalDate().toString();
	}
				
	//-------------------- milliseconds timestamp to YYYYMMDD ---------------------------
	public String SimpleMilliToYYMMDD( long milliseconds )
	 throws Exception
	{
		Timestamp timestamp = new Timestamp( milliseconds );
		
		StringBuilder sb = new StringBuilder();
		sb.append( timestamp.toLocalDateTime().getYear() )
		  .append( timestamp.toLocalDateTime().getMonthValue() )
		  .append( timestamp.toLocalDateTime().getDayOfMonth() );
		
		return sb.toString();
	}
				
	//-------------------- milliseconds to zoned date time ISO-8601 ---------------------
	public String MilliToZDT( long milliseconds )
	 throws Exception
	{
		Instant instant = Instant.ofEpochMilli( milliseconds );
		ZonedDateTime utc = ZonedDateTime.ofInstant( instant, ZoneOffset.UTC );
		
		return utc.toString();
	}
					
	//-------------------- string YYMMDD to get milliseconds ----------------------------			
	public String YYMMDDtoMilliLDTOffset( String yyyymmdd )
	 throws Exception
	{
		if( yyyymmdd.length() == TimeMachineSettings.YYYYMMDD_LENGTH )
		{
			LocalDate ld = LocalDate.of( Integer.parseInt( yyyymmdd.substring(0, 4) )
						                ,Integer.parseInt( yyyymmdd.substring(4, 6) )
						                ,Integer.parseInt( yyyymmdd.substring(6, 8) ) );	
			LocalTime lt = LocalTime.of( 0, 0, 0 );
			
			Instant ldt = LocalDateTime.of( ld, lt ).toInstant( ZoneOffset.UTC );	
				
			return ldt.toString();
		}	
		else
		{
			throw new Exception( "Invalid YYYYMMDD Length" );
		}
	}
	
	//-------------------- string YYMMDD to get milliseconds ----------------------------
	public long YYMMDDtoMilliEpochOffset( String yyyymmdd )
	 throws Exception
	{
		if( yyyymmdd.length() == TimeMachineSettings.YYYYMMDD_LENGTH )
		{
			LocalDate ld = LocalDate.of( Integer.parseInt( yyyymmdd.substring(0, 4) )
						                ,Integer.parseInt( yyyymmdd.substring(4, 6) )
						                ,Integer.parseInt( yyyymmdd.substring(6, 8) ) );	
			LocalTime lt = LocalTime.of( 0, 0, 0 );
			
			Instant ldt = LocalDateTime.of( ld, lt ).toInstant( ZoneOffset.UTC );	
			long lng = ldt.toEpochMilli();	
				
			return lng;
		}	
		else
		{
			throw new Exception( "Invalid YYYYMMDD Length" );
		}
	}
}