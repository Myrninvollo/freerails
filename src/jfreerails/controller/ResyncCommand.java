package jfreerails.controller;


/**
 * This command instructs the client to re-sync its World copy because the
 * backlog of updates exceeded the server's capacity to buffer them. You need a
 * faster connection!!
 */
class ResyncCommand extends ServerCommand {
}