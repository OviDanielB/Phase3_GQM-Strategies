package it.uniroma2.isssr.gqm3.model.validation;

public enum Countermeasure {
	// devo settare delle variabili di activiti con scope local. state già c'è,
	// va fatto l'update di questa variabile
	REPEATE_MEASURE, // => cambia lo stato da due a tre e setto il messaggio di
						// errore
	// e l'id del collectdata che si sta validando
	CONTROL_METRIC, // => cambia lo stato da due a tre e setto il messaggio di
					// errore
	EXCLUDE_DATA;
}
