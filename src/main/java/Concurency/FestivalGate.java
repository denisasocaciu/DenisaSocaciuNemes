package Concurency;

import java.util.ArrayList;
import java.util.Collections;

public class FestivalGate {

        ArrayList<TicketType> tickets = new ArrayList<>();

        boolean isClosed= true;

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public void addTicket(TicketType ticketType){
            tickets.add(ticketType);
        }

        void countTickets(){

            System.out.println();

            System.out.println("----------------- LIVE STATS ---------------");
            System.out.println("Total tickets sold: " + tickets.size());
            System.out.println("FULLVIP " + Collections.frequency(tickets, TicketType.FULLVIP));
            System.out.println("FULL " + Collections.frequency(tickets, TicketType.FULL));
            System.out.println("FREEPASS " + Collections.frequency(tickets, TicketType.FREEPASS));
            System.out.println("ONEDAY " + Collections.frequency(tickets, TicketType.ONEDAY));
            System.out.println("ONEDAYVIP " + Collections.frequency(tickets, TicketType.ONEDAYVIP));

            System.out.println();

        }

    }

