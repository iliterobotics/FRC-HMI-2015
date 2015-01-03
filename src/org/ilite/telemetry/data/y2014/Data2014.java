package org.ilite.telemetry.data.y2014;

import static org.ilite.telemetry.data.y2014.EData2014.ANAIN1_GYRO;
import static org.ilite.telemetry.data.y2014.EData2014.ANAIN2_COMPRESSOR_PRESSURE;
import static org.ilite.telemetry.data.y2014.EData2014.ANAIN3;
import static org.ilite.telemetry.data.y2014.EData2014.ANAIN4;
import static org.ilite.telemetry.data.y2014.EData2014.ANAIN5;
import static org.ilite.telemetry.data.y2014.EData2014.ANAIN6;
import static org.ilite.telemetry.data.y2014.EData2014.ANAIN7;
import static org.ilite.telemetry.data.y2014.EData2014.ANAIN8;
import static org.ilite.telemetry.data.y2014.EData2014.DIGIN10_WINCH_LIMIT_SWITCH;
import static org.ilite.telemetry.data.y2014.EData2014.DIGIN11;
import static org.ilite.telemetry.data.y2014.EData2014.DIGIN12;
import static org.ilite.telemetry.data.y2014.EData2014.DIGIN13;
import static org.ilite.telemetry.data.y2014.EData2014.DIGIN14;
import static org.ilite.telemetry.data.y2014.EData2014.DIGIN1_ENC1A;
import static org.ilite.telemetry.data.y2014.EData2014.DIGIN2_ENC1B;
import static org.ilite.telemetry.data.y2014.EData2014.DIGIN3_ENC2A;
import static org.ilite.telemetry.data.y2014.EData2014.DIGIN4_ENC2B;
import static org.ilite.telemetry.data.y2014.EData2014.DIGIN5;
import static org.ilite.telemetry.data.y2014.EData2014.DIGIN6;
import static org.ilite.telemetry.data.y2014.EData2014.DIGIN7;
import static org.ilite.telemetry.data.y2014.EData2014.DIGIN8;
import static org.ilite.telemetry.data.y2014.EData2014.DIGIN9_COMPRESSOR_PRESSURE_SWITCH;
import static org.ilite.telemetry.data.y2014.EData2014.PNEU_P1;
import static org.ilite.telemetry.data.y2014.EData2014.PNEU_P2;
import static org.ilite.telemetry.data.y2014.EData2014.PNEU_P3;
import static org.ilite.telemetry.data.y2014.EData2014.PNEU_P4;
import static org.ilite.telemetry.data.y2014.EData2014.PNEU_P5;
import static org.ilite.telemetry.data.y2014.EData2014.PNEU_P6;
import static org.ilite.telemetry.data.y2014.EData2014.PNEU_P7;
import static org.ilite.telemetry.data.y2014.EData2014.PNEU_P8;
import static org.ilite.telemetry.data.y2014.EData2014.PWM_DELIVERY_1;
import static org.ilite.telemetry.data.y2014.EData2014.PWM_DELIVERY_2;
import static org.ilite.telemetry.data.y2014.EData2014.PWM_INTAKE_1;
import static org.ilite.telemetry.data.y2014.EData2014.PWM_INTAKE_2;
import static org.ilite.telemetry.data.y2014.EData2014.PWM_LEFT_DRIVETRAIN_1;
import static org.ilite.telemetry.data.y2014.EData2014.PWM_LEFT_DRIVETRAIN_2;
import static org.ilite.telemetry.data.y2014.EData2014.PWM_RIGHT_DRIVETRAIN_1;
import static org.ilite.telemetry.data.y2014.EData2014.PWM_RIGHT_DRIVETRAIN_2;
import static org.ilite.telemetry.data.y2014.EData2014.PWM_SERVO_1;
import static org.ilite.telemetry.data.y2014.EData2014.PWM_SERVO_2;
import static org.ilite.telemetry.data.y2014. EData2014.RELAY_COMPRESSOR;
import static org.ilite.telemetry.data.y2014.EData2014.RELAY_OPEN_1;
import static org.ilite.telemetry.data.y2014.EData2014.RELAY_OPEN_2;
import static org.ilite.telemetry.data.y2014.EData2014.RELAY_SIGNAL_1;
import static org.ilite.telemetry.data.y2014.EData2014.RELAY_SIGNAL_2;
import static org.ilite.telemetry.data.y2014.EData2014.RELAY_SPARE_COMPRESSOR;
import static org.ilite.telemetry.data.y2014.EData2014.RELAY_SPARE_WINDOW_MOTOR;
import static org.ilite.telemetry.data.y2014.EData2014.RELAY_WINDOW_MOTOR;
import org.ilite.telemetry.data.DriverInput;
import org.ilite.telemetry.data.ETelemetryType;
import org.ilite.telemetry.data.FrcConstants;
import org.ilite.telemetry.data.StdDataModel;
import org.ilite.util.gui.builder.IRenderedDataModel;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JTextField;

public class Data2014 extends StdDataModel implements IRenderedDataModel<EData2014>
{
	public final static Class<? extends JComponent> scDefaultComponent = JTextField.class;

	public final static Map<Integer, EData2014> scUsedFields = new HashMap<>();

	// Portions of the enumerations that the display cares about - this is
	// not typically the entire telemetry dataset, but it can be.
	public static final EData2014[] sALL_PWMS = new EData2014[]
	{
		PWM_RIGHT_DRIVETRAIN_1,
		PWM_RIGHT_DRIVETRAIN_2,
		PWM_LEFT_DRIVETRAIN_1,
		PWM_LEFT_DRIVETRAIN_2,
		PWM_INTAKE_1,
		PWM_INTAKE_2,
		PWM_DELIVERY_1,
		PWM_DELIVERY_2,
		PWM_SERVO_1,
		PWM_SERVO_2,
	};
	public static final EData2014[] sALL_RELAYS = new EData2014[]
	{
		RELAY_COMPRESSOR,
		RELAY_SIGNAL_1,
		RELAY_SIGNAL_2,
		RELAY_WINDOW_MOTOR,
		RELAY_SPARE_COMPRESSOR,
		RELAY_SPARE_WINDOW_MOTOR,
		RELAY_OPEN_1,
		RELAY_OPEN_2,
	};
	public static final EData2014[] sALL_ANALOGS = new EData2014[]
	{
		ANAIN1_GYRO,
		ANAIN2_COMPRESSOR_PRESSURE,
		ANAIN3,
		ANAIN4,
		ANAIN5,
		ANAIN6,
		ANAIN7,
		ANAIN8
	};
	public static final EData2014[] sALL_DIGITAL_IN = new EData2014[]
	{
		DIGIN1_ENC1A,
		DIGIN2_ENC1B,
		DIGIN3_ENC2A,
		DIGIN4_ENC2B,
		DIGIN5,
		DIGIN6,
		DIGIN7,
		DIGIN8,
		DIGIN9_COMPRESSOR_PRESSURE_SWITCH,
		DIGIN10_WINCH_LIMIT_SWITCH,
		DIGIN11,
		DIGIN12,
		DIGIN13,
		DIGIN14,
	};
	public static final EData2014[] sALL_PNEUMATICS = new EData2014[]
	{
		PNEU_P1,
		PNEU_P2,
		PNEU_P3,
		PNEU_P4,
		PNEU_P5,
		PNEU_P6,
		PNEU_P7,
		PNEU_P8,
	};

	private static final EnumMap<ETelemetryType, EData2014[]> scDataMappings = 
			new EnumMap<>(ETelemetryType.class);

			static
			{    
				FrcConstants.sNUM_ANALOG_INPUTS = sALL_ANALOGS.length;
				FrcConstants.sNUM_DIGITAL_INPUTS = sALL_DIGITAL_IN.length;
				FrcConstants.sNUM_PNEUMATIC_OUTPUTS = sALL_PNEUMATICS.length;
				FrcConstants.sNUM_PWM_OUTPUTS = sALL_PWMS.length;
				FrcConstants.sNUM_RELAY_OUTPUTS = sALL_RELAYS.length;

				scDataMappings.put(ETelemetryType.ANALOG_IN, sALL_ANALOGS);
				scDataMappings.put(ETelemetryType.DIGITAL_IN, sALL_DIGITAL_IN);
				scDataMappings.put(ETelemetryType.PNEUMATIC, sALL_PNEUMATICS);
				scDataMappings.put(ETelemetryType.PWM_OUT, sALL_PWMS);
				scDataMappings.put(ETelemetryType.RELAY_OUT, sALL_RELAYS);

				int index = 0;
				for(EData2014 field : EData2014.values())
				{
					if(field.isUsed())
					{
						scUsedFields.put(index, field);
						index++;
					}
				}
			}

			/**
			 * Creates a StdDataModel object with 2 Joysticks and a FTC Gamepad
			 */
			 public Data2014()
			{
				 super(new DriverInput[]{
						 DriverInput.generateLogitechAttack3Joystick(0),
						 DriverInput.generateLogitechAttack3Joystick(1),
						 DriverInput.generateLogitechFTCGamepad(0)
				 });
			}

			 public void set(EData2014 pField, Object pValue)
			 {
				 int index = indexOf(pField);
				 switch(pField.getTelemetryType())
				 {
				 case ANALOG_IN:
					 mAnalogInputs[index] = (Float)pValue;
					 break;
				 case DIGITAL_IN:
					 mDigitalInputs[index] = (Float)pValue;
					 break;
				 case PNEUMATIC:
					 mPneumatics[index] = (Boolean)pValue;
					 break;
				 case PWM_OUT:
					 mPwmOutputs[index] = (Float)pValue;
					 break;
				 case RELAY_OUT:
					 mRelayOutputs[index] = (Integer)pValue;
					 break;
				 }
			 }

			 private int indexOf(EData2014 pField)
			 {
				 int index = pField.ordinal();
				 index -= scDataMappings.get(pField.getTelemetryType())[0].ordinal();
				 return index;
			 }

			 /**
			  * Retrieves a piece of data from the appropriate array in the parent class
			  * @param pField
			  * @return
			  */
			 public Object get(EData2014 pField)
			 {
				 Object result = null;
				 int index = indexOf(pField);
				 switch(pField.getTelemetryType())
				 {
				 	case ANALOG_IN:
				 		result = mAnalogInputs[index];
				 		break;
				 	case DIGITAL_IN:
				 		result = mDigitalInputs[index];
				 		break;
				 	case PNEUMATIC:
				 		result = mPneumatics[index];
				 		break;
				 	case PWM_OUT:
				 		result = mPwmOutputs[index];
				 		break;
				 	case RELAY_OUT:
				 		result = mRelayOutputs[index];
				 		break;
				 }
				 return result;
			 }

			 @Override
			 public <T> T getFieldValue(EData2014 pField, Class<T> pFieldClass)
			 {    
				 return pFieldClass.cast(get(pField));  
			 }
}
