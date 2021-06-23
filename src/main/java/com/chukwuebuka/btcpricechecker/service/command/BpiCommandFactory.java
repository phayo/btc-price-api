package com.chukwuebuka.btcpricechecker.service.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.chukwuebuka.btcpricechecker.domain.CurrencySymbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BpiCommandFactory {
    private Map<CurrencySymbol, BpiCommand> factory;

    @Autowired
    public BpiCommandFactory(Set<BpiCommand> delegateCommandSet){
        aggregateCommands(delegateCommandSet);
    }

    private void aggregateCommands(final Set<BpiCommand> delegateCommandSet) {
        factory = new HashMap<>();
        delegateCommandSet.forEach(bpiCommand -> factory.put(bpiCommand.getCurrencySymbol(), bpiCommand));
    }

    public BpiCommand getCommand(CurrencySymbol currencySymbol){
        return factory.get(currencySymbol);
    }
}
