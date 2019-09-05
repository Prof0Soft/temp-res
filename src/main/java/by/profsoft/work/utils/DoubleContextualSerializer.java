package by.profsoft.work.utils;

import by.profsoft.work.utils.annatation.Precision;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 */
public class DoubleContextualSerializer extends JsonSerializer<Double> implements ContextualSerializer {

    private int precision = 0;

    /**
     * Constructor with depends.
     *
     * @param precision the precision.
     */
    public DoubleContextualSerializer(final int precision) {
        this.precision = precision;
    }

    /**
     * Default constructor.
     */
    public DoubleContextualSerializer() {

    }

    @Override
    public void serialize(final Double value, final JsonGenerator gen,
                          final SerializerProvider serializers) throws IOException, JsonProcessingException {
        if (precision == 0) {
            gen.writeNumber(value.doubleValue());
        } else {
            BigDecimal bd = new BigDecimal(value);
            bd = bd.setScale(precision, RoundingMode.HALF_UP);
            gen.writeNumber(bd.doubleValue());
        }

    }

    @Override
    public JsonSerializer<?> createContextual(final SerializerProvider prov,
                                              final BeanProperty property) throws JsonMappingException {
        Precision precision = property.getAnnotation(Precision.class);
        if (precision != null) {
            return new DoubleContextualSerializer(precision.precision());
        }
        return this;
    }
}
